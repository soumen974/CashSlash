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

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Group extends Fragment {

    private Dialog myDialog;
    private Button createGroupButton;
    private EditText groupNameEditText;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private RecyclerView groupRecyclerView;
    private GroupAdapter adapter;
    private ArrayList<GroupsCr> groupsCrArrayList;

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

        // Initialize RecyclerView
        groupRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        groupsCrArrayList = new  ArrayList<GroupsCr>();
        adapter = new GroupAdapter(Group.this, groupsCrArrayList);

        // Retrieve group data from Firebase
        DatabaseReference reference = database.getReference().child("groups").child(auth.getCurrentUser().getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                groupsCrArrayList.clear();
                for (DataSnapshot groupSnapshot : snapshot.getChildren()) {
                    String gpname = groupSnapshot.child("gpname").getValue(String.class);
                    GroupsCr groupsCr = new GroupsCr(gpname);
                    groupsCrArrayList.add(groupsCr);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle onCancelled if needed
            }
        });

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
