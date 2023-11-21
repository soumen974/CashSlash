//package com.example.cashslash;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Groupchat extends AppCompatActivity {
//    private EditText Friend1;
//    private EditText Friend2;
//    private EditText Friend3;
//    private EditText Friend4;
//    private EditText Amount1;
//    private EditText Amount2;
//    private EditText Amount3;
//    private EditText Amount4;
//    private Button calculateButton;
//    private RecyclerView recyclerView;
//
//    private List<String> paymentDetailsList;
//    private List<String> receivingDetailsList;
//    private PaymentAdapter paymentAdapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_groupchat);
//
//        Friend1 = findViewById(R.id.user1);
//        Friend2 = findViewById(R.id.user2);
//        Friend3 = findViewById(R.id.user3);
//        Friend4 = findViewById(R.id.user4);
//        Amount1 = findViewById(R.id.amount1);
//        Amount2 = findViewById(R.id.amount2);
//        Amount3 = findViewById(R.id.amount3);
//        Amount4 = findViewById(R.id.amount4);
//
//        calculateButton = findViewById(R.id.button);
//        recyclerView = findViewById(R.id.recyclerView);
//
//        paymentDetailsList = new ArrayList<>();
//        receivingDetailsList = new ArrayList<>();
//        paymentAdapter = new PaymentAdapter(paymentDetailsList, receivingDetailsList);
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(paymentAdapter);
//
//        calculateButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                calculateAndDisplayPayments();
//            }
//        });
//    }
//
//    private void calculateAndDisplayPayments() {
//        String[] friendNames = {
//                Friend1.getText().toString(),
//                Friend2.getText().toString(),
//                Friend3.getText().toString(),
//                Friend4.getText().toString()
//        };
//        double[] expenses = {
//                Double.parseDouble(Amount1.getText().toString()),
//                Double.parseDouble(Amount2.getText().toString()),
//                Double.parseDouble(Amount3.getText().toString()),
//                Double.parseDouble(Amount4.getText().toString())
//        };
//
//        // Calculate payment and receiving details
//        double totalExpense = 0;
//        for (double expense : expenses) {
//            totalExpense += expense;
//        }
//        double averageExpense = totalExpense / friendNames.length;
//
//        for (int i = 0; i < friendNames.length; i++) {
//            double difference = expenses[i] - averageExpense;
//            if (difference > 0) {
//                // This person needs to receive money
//                receivingDetailsList.add(friendNames[i] + " receives $" + difference);
//            } else if (difference < 0) {
//                // This person needs to pay money
//                paymentDetailsList.add(friendNames[i] + " pays $" + Math.abs(difference));
//            }
//        }
//
//        // Display payment details
//        paymentDetailsList.add(0, "Payment details:");
//        paymentAdapter.notifyDataSetChanged();
//
//        // Display receiving details
//        receivingDetailsList.add(0, "Receiving details:");
//        paymentAdapter.notifyDataSetChanged();
//    }
//        paymentDetailsList.clear();
//        paymentDetailsList.add("Payment details:");
//        for (String detail : paymentDetailsList) {
//            paymentDetailsList.add(detail);
//        }
//        paymentAdapter.notifyDataSetChanged();
//
//        // Display receiving details
//        receivingDetailsList.clear();
//        receivingDetailsList.add("Receiving details:");
//        for (String detail : receivingDetailsList) {
//            receivingDetailsList.add(detail);
//        }
//        paymentAdapter.notifyDataSetChanged();
//    }
//}


package com.example.cashslash;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Groupchat extends AppCompatActivity {
    private EditText Friend1;
    private EditText Friend2;
    private EditText Friend3;
    private EditText Friend4;
    private EditText Amount1;
    private EditText Amount2;
    private EditText Amount3;
    private EditText Amount4;
    private Button calculateButton;
    private RecyclerView recyclerView;

    private List<String> paymentDetailsList;
    private List<String> receivingDetailsList;
    private PaymentAdapter paymentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groupchat);

        Friend1 = findViewById(R.id.user1);
        Friend2 = findViewById(R.id.user2);
        Friend3 = findViewById(R.id.user3);
        Friend4 = findViewById(R.id.user4);
        Amount1 = findViewById(R.id.amount1);
        Amount2 = findViewById(R.id.amount2);
        Amount3 = findViewById(R.id.amount3);
        Amount4 = findViewById(R.id.amount4);

        calculateButton = findViewById(R.id.button);
        recyclerView = findViewById(R.id.recyclerView);

        paymentDetailsList = new ArrayList<>();
        receivingDetailsList = new ArrayList<>();
        paymentAdapter = new PaymentAdapter(paymentDetailsList, receivingDetailsList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(paymentAdapter);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateAndDisplayPayments();
            }
        });
    }

    private void calculateAndDisplayPayments() {
        String[] friendNames = {
                Friend1.getText().toString(),
                Friend2.getText().toString(),
                Friend3.getText().toString(),
                Friend4.getText().toString()
        };
        double[] expenses = {
                Double.parseDouble(Amount1.getText().toString()),
                Double.parseDouble(Amount2.getText().toString()),
                Double.parseDouble(Amount3.getText().toString()),
                Double.parseDouble(Amount4.getText().toString())
        };

        // Calculate payment and receiving details
        double totalExpense = 0;
        for (double expense : expenses) {
            totalExpense += expense;
        }
        double averageExpense = totalExpense / friendNames.length;

        for (int i = 0; i < friendNames.length; i++) {
            double difference = expenses[i] - averageExpense;
            if (difference > 0) {
                // This person needs to receive money
                receivingDetailsList.add(friendNames[i] + " receives $" + difference);
            } else if (difference < 0) {
                // This person needs to pay money
                paymentDetailsList.add(friendNames[i] + " pays $" + Math.abs(difference));
            }
        }

        // Display payment and receiving details
        paymentDetailsList.add(0, "Payment details:");
        receivingDetailsList.add(0, "Receiving details:");

        paymentAdapter.notifyDataSetChanged();
    }


}

