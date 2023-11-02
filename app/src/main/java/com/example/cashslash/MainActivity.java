package com.example.cashslash;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth auth;
    Button SaveCh;
    EditText Fname, Lname;
    CircleImageView rg_profileImg;
    Uri imageURI;
    String userId; // User ID from the sign-up

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

        // Get the user ID passed from SignUp
        userId = getIntent().getStringExtra("userId");

        SaveCh = findViewById(R.id.save);
        Fname = findViewById(R.id.inputfirstname);
        Lname = findViewById(R.id.inputlastname);
        rg_profileImg = findViewById(R.id.profile);

        rg_profileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement code for selecting an image
            }
        });

        SaveCh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String FirstName = Fname.getText().toString();
                String LastName = Lname.getText().toString();
                if (TextUtils.isEmpty(FirstName) || TextUtils.isEmpty(LastName)) {
                    Toast.makeText(MainActivity.this, "Please provide your name", Toast.LENGTH_SHORT).show();
                } else {
                    DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("user").child(userId);
                    userRef.child("firstName").setValue(FirstName);
                    userRef.child("lastName").setValue(LastName);

                    Toast.makeText(MainActivity.this, "Name updated successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==10)
        {
            if (data != null){
                imageURI = data.getData();
                rg_profileImg.setImageURI(imageURI);
            }
        }
    }
}