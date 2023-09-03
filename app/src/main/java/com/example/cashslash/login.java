package com.example.cashslash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {
    private EditText User;
    private EditText Pass;
    private EditText Repass;
    private Button Logup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        User = (EditText)findViewById(R.id.lemail);
        Pass = (EditText)findViewById(R.id.lpassword);
        Repass = (EditText)findViewById(R.id.lrepassword);
        Logup =(Button)findViewById(R.id.login2);

        Logup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(login.this,"Loing succesfully",Toast.LENGTH_LONG).show();
            }
        });
    }
    public void sign(View view) {
        Intent intent = new Intent(login.this, signin.class);
        startActivity(intent);
    }
    public void backk(View view) {
        Intent intent = new Intent(login.this, Landing_page.class);
        startActivity(intent);
    }
}