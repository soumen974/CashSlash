package com.example.cashslash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    private EditText Email;
    private EditText Pass;
    private EditText Repass;
    private Button Signup;
    private Button back;

    String EmailPattern = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$";
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        auth = FirebaseAuth.getInstance();

        Email = findViewById(R.id.semail);
        Pass = findViewById(R.id.spassword);
        Repass = findViewById(R.id.srepassword);
        Signup = findViewById(R.id.signup);

        back = findViewById(R.id.backlogin);

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = Email.getText().toString();
                String pass = Pass.getText().toString();
                String repass = Repass.getText().toString();
                String status = "I am using CashSlash";
                if (TextUtils.isEmpty(mail) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(repass)) {
                    Toast.makeText(SignUp.this, "Please enter valid information", Toast.LENGTH_SHORT).show();
                } else if (!mail.matches(EmailPattern)) {
                    Email.setError("Type a valid Email");
                } else if (pass.length() < 6) {
                    Pass.setError("Password must be 6 characters or more");
                } else if (!pass.equals(repass)) {
                    Pass.setError("Password doesn't match");
                } else {
                    auth.createUserWithEmailAndPassword(mail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                String id = task.getResult().getUser().getUid();
                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("user").child(id);

                                Users users = new Users(id, mail, pass, status, "", "","");
                                reference.setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Intent intent = new Intent(SignUp.this, MainActivity.class);
                                            intent.putExtra("userId", id);
                                            startActivity(intent);
                                        } else {
                                            Toast.makeText(SignUp.this, "Error in Signup", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(SignUp.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public void sign(View view) {
        Intent intent = new Intent(SignUp.this, Login.class);
        startActivity(intent);
    }
}
