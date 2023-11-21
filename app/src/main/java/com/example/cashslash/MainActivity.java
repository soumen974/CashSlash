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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseStorage storage;
    Button SaveCh;
    EditText Fname, Lname;
    CircleImageView rg_profileImg;
    Uri imageURI;
    String imageuri;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();

        userId = getIntent().getStringExtra("userId");

        SaveCh = findViewById(R.id.save);
        Fname = findViewById(R.id.inputfirstname);
        Lname = findViewById(R.id.inputlastname);
        rg_profileImg = findViewById(R.id.profile);



        rg_profileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Picture"),10);
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
                    StorageReference storageReference = storage.getReference().child("upload").child(userId);

                    if (imageURI != null) {
                        storageReference.putFile(imageURI).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                if (task.isSuccessful()) {
                                    // Image uploaded successfully
                                    storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            // Get the download URL of the image
                                            imageuri = uri.toString();

                                            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("user").child(userId);
                                            userRef.child("firstName").setValue(FirstName);
                                            userRef.child("lastName").setValue(LastName);
                                            userRef.child("profilepic").setValue(imageuri);

                                            Intent intent = new Intent(MainActivity.this, Homegroup.class);
                                            startActivity(intent);
                                            finish();

                                            Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                } else {
                                    // Error in image upload
                                    Toast.makeText(MainActivity.this, "Error in Saving", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else {
                        // Default image URL or error handling
                        imageuri = "https://firebasestorage.googleapis.com/v0/b/cashslash-3e60f.appspot.com/o/useimg.png?alt=media&token=604fb269-461b-4df0-9bb6-5a406dcf86f6";

                        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("user").child(userId);
                        userRef.child("firstName").setValue(FirstName);
                        userRef.child("lastName").setValue(LastName);
                        userRef.child("profilepic").setValue(imageuri);

                        Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                    }
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