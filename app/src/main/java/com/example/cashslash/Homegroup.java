package com.example.cashslash;

import android.content.Intent;
import android.os.Bundle;

import com.example.cashslash.databinding.ActivityHomegroupBinding;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Homegroup extends AppCompatActivity {
    ActivityHomegroupBinding binding;
    private FirebaseAuth auth;
    private DatabaseReference userRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomegroupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        userRef = FirebaseDatabase.getInstance().getReference().child("user").child(auth.getCurrentUser().getUid());

        // Retrieve user data
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    // Get user data
                    String firstName = snapshot.child("firstName").getValue(String.class);
                    String lastName = snapshot.child("lastName").getValue(String.class);
                    String profilePicUrl = snapshot.child("profilepic").getValue(String.class);

                    // Display user data
                    binding.showName.setText(firstName + " " + lastName);

                    // Use Picasso to load the profile picture
                    Picasso.get().load(profilePicUrl).into(binding.profile);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle onCancelled if needed
            }
        });

        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.group) {
                replaceFragment(new Group());
            } else if (item.getItemId() == R.id.people) {
                replaceFragment(new People());
            } else if (item.getItemId() == R.id.history) {
                replaceFragment(new History());
            } else if (item.getItemId() == R.id.chats) {
                replaceFragment(new Chat());
            }

            return true;
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }
}
