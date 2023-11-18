package com.example.cashslash;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Group extends Fragment {

    private Dialog myDialog;
    private Button createGroupButton;
    private EditText groupNameEditText;
    private FirebaseAuth auth;
    FirebaseDatabase database;
    RecyclerView groupRecyclerView;
    GroupAdapter adapter;
//    ArrayList<GroupsCr>;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_group, container, false);

        myDialog = new Dialog(requireContext());
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        groupRecyclerView = rootView.findViewById(R.id.grouplist);


        createGroupButton = rootView.findViewById(R.id.createGroup);
        groupNameEditText = rootView.findViewById(R.id.GroupName);

        createGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showGroupCreationPopup();
            }
        });
    //Group view
        DatabaseReference reference = database.getReference().child("groups");
//        groupRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        groupRecyclerView.setAdapter(adapter);

        return rootView;
    }

    private void showGroupCreationPopup() {
        myDialog.setContentView(R.layout.group_popup);

        Button createButton = myDialog.findViewById(R.id.createggrop);
        EditText groupNameEditTextPopup = myDialog.findViewById(R.id.GroupName);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String groupName = groupNameEditTextPopup.getText().toString().trim();
                if (!groupName.isEmpty()) {
                    // Call a method to store the group name in the database
                    storeGroupNameInDatabase(groupName);
                    myDialog.dismiss(); // Dismiss the popup after creating the group
                } else {
                    Toast.makeText(requireContext(), "Please enter a group name", Toast.LENGTH_SHORT).show();
                }
            }
        });

        myDialog.show();
    }

//    private void storeGroupNameInDatabase(String groupName) {
//        if (auth.getCurrentUser() != null) {
//            String userId = auth.getCurrentUser().getUid();
//            DatabaseReference groupRef = FirebaseDatabase.getInstance().getReference().child("groups").child(userId);
//
//            // You can customize the structure of the database as needed
//            String groupId = groupRef.push().getKey(); // Generate a unique key for the group
//            groupRef.child(groupId).setValue(groupName);
//        } else {
//            Toast.makeText(requireContext(), "User not authenticated", Toast.LENGTH_SHORT).show();
//        }
//    }
private void storeGroupNameInDatabase(String groupName) {
    if (auth.getCurrentUser() != null) {
        String userId = auth.getCurrentUser().getUid();
        DatabaseReference groupRef = FirebaseDatabase.getInstance().getReference().child("groups").child(userId);

        // You can customize the structure of the database as needed
        String groupId = groupRef.push().getKey(); // Generate a unique key for the group
        groupRef.child(groupId).setValue(new GroupsCr(groupName));
        } else {
            Toast.makeText(requireContext(), "User not authenticated", Toast.LENGTH_SHORT).show();
        }
    }
}
