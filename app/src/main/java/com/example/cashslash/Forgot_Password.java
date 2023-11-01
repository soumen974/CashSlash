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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Forgot_Password extends AppCompatActivity {

    private Button btnforgot, backkk;
    private EditText mailforgot;
    String strEmail;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);

        btnforgot = findViewById(R.id.update);
        mailforgot = findViewById(R.id.forgotmail);
        backkk = findViewById(R.id.backbtnn);

        auth = FirebaseAuth.getInstance();

        btnforgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strEmail = mailforgot.getText().toString();
                if (!TextUtils.isEmpty(strEmail)){
                    ResetPassword();
                } else {
                    mailforgot.setError("Please provide an email");
                }
            }
        });

        backkk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void ResetPassword() {
        btnforgot.setVisibility(View.INVISIBLE);

        auth.sendPasswordResetEmail(strEmail).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                // Password reset email sent successfully

                // Now, update the password in Firebase Realtime Database
                FirebaseUser user = auth.getCurrentUser();
                if (user != null) {
                    String userId = user.getUid();
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference userRef = database.getReference("user").child(userId);

                    // Set the new password in the Realtime Database
                    userRef.child("password").setValue("new_password_here");

                    Toast.makeText(Forgot_Password.this, "Reset Password link has been sent to your registered email", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Forgot_Password.this, Login.class);
                    startActivity(intent);
                    finish();
                } else {
                    // Handle the case where the user is not authenticated
                    Toast.makeText(Forgot_Password.this, "User not authenticated", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Forgot_Password.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                btnforgot.setVisibility(View.VISIBLE);
            }
        });
    }
}
