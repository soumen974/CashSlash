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
        Intent intent = new Intent(Landing_page.this, login.class);
        startActivity(intent);
    }

    public void sin(View view) {
        Intent intent1 = new Intent(Landing_page.this, signin.class);
        startActivity(intent1);
    }
}