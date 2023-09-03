package com.example.cashslash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signin extends AppCompatActivity {

    private EditText User;
    private EditText Pass;
    private Button Signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        User = (EditText)findViewById(R.id.email);
        Pass = (EditText)findViewById(R.id.password);
        Signin =(Button)findViewById(R.id.signinn);

        Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (User.getText().toString().equals("ritesh@gmail.com")&& Pass.getText().toString().equals("12345"))
                {
                    Toast.makeText(signin.this,"Signup succesfully",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(signin.this,"signup Unsuccesfully",Toast.LENGTH_LONG).show();

                }

            }
        });
    }
    public void logup(View view) {
        Intent intent = new Intent(signin.this, login.class);
        startActivity(intent);
    }
    public void back(View view) {
        Intent intent = new Intent(signin.this, Landing_page.class);
        startActivity(intent);
    }
}