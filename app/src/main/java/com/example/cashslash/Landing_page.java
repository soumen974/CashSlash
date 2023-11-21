package com.example.cashslash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class Landing_page extends AppCompatActivity {

    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

//        Loged in user
        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            // User is already authenticated, redirect to Homegroup activity
            startActivity(new Intent(Landing_page.this, Homegroup.class));
            finish(); // Finish the current activity to prevent going back to the login screen
        }
    }

    public void log(View view) {
        Intent intent = new Intent(Landing_page.this, Login.class);
        startActivity(intent);
        finish();
    }

    public void sin(View view) {
        Intent intent1 = new Intent(Landing_page.this, SignUp.class);
        startActivity(intent1);
        finish();
    }
}