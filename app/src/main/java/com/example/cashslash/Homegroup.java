package com.example.cashslash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.cashslash.databinding.ActivityHomegroupBinding;

public class Homegroup extends AppCompatActivity {
    ActivityHomegroupBinding binding;


    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomegroupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new Group());

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
        fragmentTransaction.replace(R.id.container,fragment);
        fragmentTransaction.commit();
    }
}