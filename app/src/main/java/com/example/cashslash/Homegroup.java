package com.example.cashslash;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.example.cashslash.databinding.ActivityHomegroupBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Homegroup extends AppCompatActivity {
    ActivityHomegroupBinding binding;
    private FirebaseAuth auth;
    private DatabaseReference userRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomegroupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//
//        auth = FirebaseAuth.getInstance();
//        userRef = FirebaseDatabase.getInstance().getReference("user");

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
//
//        // Load user details (name and image) from Firebase
//        loadUserDetails();
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }
//
//    private void loadUserDetails() {
//        FirebaseUser currentUser = auth.getCurrentUser();
//        if (currentUser != null) {
//            String userId = currentUser.getUid();
//            DatabaseReference currentUserRef = userRef.child(userId);
//
//            currentUserRef.addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                        Users user = snapshot.getValue(Users.class);
//                        if (user != null) {
//                            // Now you can use the 'user' object
//                            String profilePicUrl = user.getProfilepic();
//                            String userName = user.getFirstName() + " " + user.getLastName();
//
//                            // Use Picasso to load the image into the CircleImageView
//                            Picasso.get().load(profilePicUrl).into(binding.profile);
//
//                            // Set the user's name in the TextView
//                            binding.showName.setText(userName);
//                        }
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//                    // Handle database error
//                    Toast.makeText(Homegroup.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//
//            });
//        }
//    }
}
