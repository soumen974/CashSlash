package com.example.cashslash;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {
    private EditText User;
    private EditText Pass;
    private EditText Repass;
    private Button Logup;

    private Button balog;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        User = (EditText)findViewById(R.id.lemail);
        Pass = (EditText)findViewById(R.id.lpassword);
        Repass = (EditText)findViewById(R.id.lrepassword);
        Logup =(Button)findViewById(R.id.login2);
        balog = (Button)findViewById(R.id.backlogin);

        Logup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = User.getText().toString();
                String pass = Pass.getText().toString();
                String repass = Repass.getText().toString();
                if(mail.length()==0 || pass.length()==0 || repass.length()==0)
                {
                    Toast.makeText(getApplicationContext(),"Please Fill all details",Toast.LENGTH_SHORT).show();
                }
                else
                {
                   if (pass.compareTo(repass)==0)
                   {
                       if ( isValid(pass) && (isValidEmail(mail)) )
                       {
                           Toast.makeText(getApplicationContext(), "Data uploaded", Toast.LENGTH_SHORT).show();
                          startActivity(new Intent(login.this, signin.class));
                       }
                       else
                       {
                           Toast.makeText(getApplicationContext(), "Please check email & password", Toast.LENGTH_SHORT).show();

                       }
                   }
                   else
                   {
                       Toast.makeText(getApplicationContext(),"Password didn't match",Toast.LENGTH_SHORT).show();
                   }
                }

            }
        });

        balog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }


    public void sign(View view) {
        Intent intent = new Intent(login.this, signin.class);
        startActivity(intent);
    }
//    public void backk(View view) {
//        Intent intent = new Intent(login.this, Landing_page.class);
//        startActivity(intent);
//    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alt = new AlertDialog.Builder(this);
        alt.setTitle("Alert !")
                .setMessage("Do you want to close")
                .setCancelable(true)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog alert = alt.create();
        alert.show();
    }


    // corection password code
    public static boolean isValid(String passwordhere) {
        int f1=0, f2=0, f3=0;
        if (passwordhere.length() <8)
        {
            return  false;
        }
        else
        {
            for (int p =0; p < passwordhere.length(); p++)
            {
                if (Character.isLetter(passwordhere.charAt(p)))
                {
                    f1=1;
                }
            }
            for (int r =0; r < passwordhere.length(); r++)
            {
                if (Character.isDigit(passwordhere.charAt(r)))
                {
                    f2=1;
                }
            }
            for (int s = 0; s < passwordhere.length(); s++)
            {
                char c = passwordhere.charAt(s);
                if(c>=33 && c<=46 || c==64)
                {
                    f3=1;
                }
            }
            if(f1==1 && f2== 1 && f3==1)
                return true;
            return  false;
        }
    }

    // Email validation check
    public static boolean isValidEmail(CharSequence target)
    {
        return Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
// should i connect database
}