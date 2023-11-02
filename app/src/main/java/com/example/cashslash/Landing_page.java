package com.example.cashslash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Landing_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
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