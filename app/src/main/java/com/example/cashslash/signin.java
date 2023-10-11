package com.example.cashslash;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
                String mail = User.getText().toString();
                String pass = Pass.getText().toString();
                if(mail.length()==0 || pass.length()==0)
                {
                    Toast.makeText(getApplicationContext(),"Please Fill all details",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Sigup Succes",Toast.LENGTH_SHORT).show();
                }


            }
        });


    }


    public void logup(View view) {
        Intent intent = new Intent(signin.this, login.class);
        startActivity(intent);
    }
    public void back(View view) {
        onBackPressed();
    }

//    @Override
////    public void onBackPressed() {
////        AlertDialog.Builder alt = new AlertDialog.Builder(this);
////        alt.setTitle("Alert !")
////                .setMessage("Do you want to close")
////                .setCancelable(false)
////                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
////                    @Override
////                    public void onClick(DialogInterface dialogInterface, int i) {
////                        finish();
////                    }
////                })
////                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
////                    @Override
////                    public void onClick(DialogInterface dialogInterface, int i) {
////                        dialogInterface.cancel();
////                    }
////                });
////        AlertDialog alert = alt.create();
////        alert.show();
////    }
}