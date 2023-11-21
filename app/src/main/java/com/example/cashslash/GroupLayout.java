// GroupLayout.java
package com.example.cashslash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cashslash.R;

public class GroupLayout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_layout);

//        TextView grNameTextView = findViewById(R.id.grname);
        RelativeLayout amtcalLayout = findViewById(R.id.Amtcal);
        // Set an OnClickListener to navigate to GroupChat activity
        amtcalLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the GroupChat activity
                Intent intent = new Intent(GroupLayout.this, Groupchat.class);
                startActivity(intent);
            }
        });
    }
}
