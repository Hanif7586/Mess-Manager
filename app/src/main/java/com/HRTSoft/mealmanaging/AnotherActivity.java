package com.HRTSoft.mealmanaging;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class AnotherActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);


        // Retrieve data from Intent
        Intent intent = getIntent();
        String fnamee = intent.getStringExtra("FNAMEE");
        String motmeal = intent.getStringExtra("MOTMEAL");

        String MOTJOMA = intent.getStringExtra("MOTJOMA");
        String MEALRATESAVE = intent.getStringExtra("MEALRATESAVE");

        String MOTKHOROJ = intent.getStringExtra("MOTKHOROJ");
        String MANAGERPABE = intent.getStringExtra("MANAGERPABE");
        String MANAGERDIBE = intent.getStringExtra("MANAGERDIBE");
        // Retrieve other data fields similarly

        // Update TextViews with the retrieved data
        TextView textViewFnamee = findViewById(R.id.textViewFnamee);
        TextView textViewMotmeal = findViewById(R.id.textViewMotmeal);

        TextView textViewMotJoma = findViewById(R.id.textViewMotJoma);
        TextView textViewMealrate = findViewById(R.id.textViewMealrate);

        TextView textViewMotkhoroj = findViewById(R.id.textViewMotkhoroj);
        TextView textViewManagerpabe = findViewById(R.id.textViewManagerpabe);
        TextView textViewManagerdibe = findViewById(R.id.textViewManagerdibe);
        // Find and update other TextViews similarly

        textViewFnamee.setText("Fnamee: " + fnamee);
        textViewMotmeal.setText("Motmeal: " + motmeal);

        textViewMotJoma.setText(": " + MOTJOMA);
        textViewMealrate.setText(": " + MEALRATESAVE);


        textViewMotkhoroj.setText(" : " + MOTKHOROJ);
        textViewManagerpabe.setText(" : " + MANAGERPABE);
        textViewManagerdibe.setText(" : " + MANAGERDIBE);

    }}