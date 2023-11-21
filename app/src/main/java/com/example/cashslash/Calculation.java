package com.example.cashslash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;


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

        firstName=findViewById(R.id.First_Person_input);
        secondName=findViewById(R.id.Second_Person_input);
        thirdName=findViewById(R.id.Third_Person_input);
        fourthName=findViewById(R.id.Fourth_Person_input);

        calculate=findViewById(R.id.calculate);

        firstAmount=findViewById(R.id.First_Person_amount);
        secondAmount=findViewById(R.id.Second_Person_amount);
        thirdAmount=findViewById(R.id.Third_person_amount);
        fourthAmount=findViewById(R.id.Fourth_person_amount);

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




        First_Person.setText(firstName.getText().toString()+" will give : "+" RS ONLY");
        Second_Person.setText(secondName.getText().toString()+" will give : "+" RS ONLY");
        Third_Person.setText(thirdName.getText().toString()+" will give : "+" RS ONLY");
        Fourth_Person.setText(fourthName.getText().toString()+" will give : "+" RS ONLY");

    }

}