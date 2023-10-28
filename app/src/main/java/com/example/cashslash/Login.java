package com.example.cashslash;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

public class Login extends AppCompatActivity {

    FirebaseAuth auth;
    String EmailPattern = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$";


    private EditText Email;
    private EditText Pass;
    private Button Loginn;

    private Button back;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        Email = (EditText)findViewById(R.id.email);
        Pass = (EditText)findViewById(R.id.password);
        Loginn =(Button)findViewById(R.id.login);
        back =(Button)findViewById(R.id.backbtn);


        Loginn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = Email.getText().toString();
                String pass = Pass.getText().toString();

                if ((TextUtils.isEmpty(mail))){
                    Toast.makeText(Login.this,"Enter the Email",Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(pass)) {
                    Toast.makeText(Login.this,"Enter the Password",Toast.LENGTH_SHORT).show();
                } else if (!mail.matches(EmailPattern)) {
                    Email.setError("Give proper Email Address");
                } else if (pass.length()<6) {
                    Pass.setError("More than 6 character");
                    Toast.makeText(Login.this,"Password longer than 6 character",Toast.LENGTH_SHORT).show();
                }else {
                    auth.signInWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                try {
                                    Intent intent = new Intent(Login.this, Homegroup.class);
                                    startActivity(intent);
                                    finish();

                                } catch (Exception e){
                                    Toast.makeText(Login.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(Login.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
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
    public void logup(View view) {
        Intent intent = new Intent(Login.this, SignUp.class);
        startActivity(intent);
    }

}