package com.example.cashslash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;


public class Calculation extends AppCompatActivity {


    TextInputEditText firstAmount,secondAmount,thirdAmount,fourthAmount;

    TextInputEditText firstName,secondName,thirdName,fourthName;
    TextView First_Person,Second_Person,Third_Person,Fourth_Person;

    Button calculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        firstName = findViewById(R.id.First_Person_input);
        secondName = findViewById(R.id.Second_Person_input);
        thirdName = findViewById(R.id.Third_Person_input);
        fourthName = findViewById(R.id.Fourth_Person_input);

        calculate = findViewById(R.id.calculate);

        firstAmount = findViewById(R.id.First_Person_amount);
        secondAmount = findViewById(R.id.Second_Person_amount);
        thirdAmount = findViewById(R.id.Third_person_amount);
        fourthAmount = findViewById(R.id.Fourth_person_amount);



//        double  a1= Double.parseDouble(firstAmount.getText().toString());
//        double  a2= Double.parseDouble(secondAmount.getText().toString());
//        double  a3= Double.parseDouble(thirdAmount.getText().toString());
//        double  a4= Double.parseDouble(fourthAmount.getText().toString());

//        String n1=firstName.getText().toString();
//        String n2=secondName.getText().toString();
//        String n3=thirdName.getText().toString();
//        String n4=fourthName.getText().toString();



        First_Person =findViewById(R.id.First_Person_cal);
        Second_Person =findViewById(R.id.Second_Person_cal);
        Third_Person =findViewById(R.id.Third_person_cal);
        Fourth_Person =findViewById(R.id.Fourth_person_cal);



    }
    public void back_btn (View view){
        Intent intent =new Intent(getApplicationContext(), Homegroup.class);
        startActivity(intent);
        finish();
    }

    public  void Calculate(View view){

        double a1 = Double.parseDouble(firstAmount.getText().toString());
        double a2 = Double.parseDouble(secondAmount.getText().toString());
        double a3 = Double.parseDouble(thirdAmount.getText().toString());
        double a4 = Double.parseDouble(fourthAmount.getText().toString());

        String n1 = firstName.getText().toString();
        String n2 = secondName.getText().toString();
        String n3 = thirdName.getText().toString();
        String n4 = fourthName.getText().toString();

        int numFriends = 4;
        String[] friendNames = {n1, n2, n3, n4};
        double[] expenses = {a1, a2, a3, a4};

        // Calculate the total expenses
        double totalExpenses = 0;
        for (double expense : expenses) {
            totalExpenses += expense;
        }
        // Calculate the average expense
        double averageExpense = totalExpenses / numFriends;

        // Calculate how much each friend owes or is owed
        double[] owes = new double[numFriends];
        for (int i = 0; i < numFriends; i++) {
            owes[i] = expenses[i] - averageExpense;
        }

        // Create a list to store payment details
        List<String> paymentDetails = new ArrayList<>();


        // Generate payment details
        for (int i = 0; i < numFriends; i++) {
            for (int j = 0; j < numFriends; j++) {
                if (i != j && owes[i] < 0 && owes[j] > 0) {
                    double amount = Math.min(Math.abs(owes[i]), owes[j]);
                    String paymentDetail = friendNames[i] + " should pay Rs- " + amount + " to " + friendNames[j];
                    paymentDetails.add(paymentDetail);
                    owes[i] += amount;
                    owes[j] -= amount;
                }
            }
        }

        // Print payment details
        StringBuilder paymentDetailsText = new StringBuilder();
        for (String detail : paymentDetails) {
            paymentDetailsText.append(detail).append("\n");
        }
        First_Person.setText(paymentDetailsText.toString());


        // Print who should receive from whom
        StringBuilder receiveDetailsText = new StringBuilder();
        for (int i = 0; i < numFriends; i++) {
            for (int j = 0; j < numFriends; j++) {
                if (i != j && owes[i] > 0 && owes[j] < 0) {
                    double amount = Math.min(Math.abs(owes[i]), Math.abs(owes[j]));
                    receiveDetailsText.append(friendNames[i])
                            .append(" should receive Rs- ")
                            .append(amount)
                            .append(" from ")
                            .append(friendNames[j])
                            .append("\n");
                    owes[i] -= amount;
                    owes[j] += amount;
                }
            }
        }
        Second_Person.setText(receiveDetailsText.toString());





//        First_Person.setText(firstName.getText().toString()+" will give : "+" RS ONLY");
//        Second_Person.setText(secondName.getText().toString()+" will give : "+" RS ONLY");
//        Third_Person.setText(thirdName.getText().toString()+" will give : "+" RS ONLY");
//        Fourth_Person.setText(fourthName.getText().toString()+" will give : "+" RS ONLY");

    }

}