package com.HRTSoft.mealmanaging;

import static com.HRTSoft.mealmanaging.DBmainm.TABLENAME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Hisab_Display_Activity extends AppCompatActivity {

    DBmainm dbHelper;
    SQLiteDatabase sqLiteDatabase;
    RecyclerView recyclerView;
    MyAdapterm adapter;
    private ArrayList<Modelm> courseModelArrayList;
    private Button send;

    // Your SharedPreferencesManager instance


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hisab_display);

        dbHelper = new DBmainm(this);
        findId();
        displayData();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Assuming you want to send the first item's data from the list
                if (courseModelArrayList != null && courseModelArrayList.size() > 0) {
                    Modelm firstItem = courseModelArrayList.get(0);

                    // Create an Intent to start the second activity
                    Intent intent = new Intent(Hisab_Display_Activity.this, AnotherActivity.class);

                    // Add data as extras to the intent
                    intent.putExtra("FNAMEE", firstItem.getFirstname());
                    intent.putExtra("MOTMEAL", firstItem.getMotMeal());
                    intent.putExtra("MOTJOMA", firstItem.getMotJoma());
                    intent.putExtra("MEALRATESAVE", firstItem.getMealRatesave());
                    intent.putExtra("MOTKHOROJ", firstItem.getMotKhoroj());
                    intent.putExtra("MANAGERPABE", firstItem.getManagerpabe());
                    intent.putExtra("MANAGERDIBE", firstItem.getManagerdibe());

                    // Start the second activity with the intent
                    startActivity(intent);
                } else {
                    // Handle the case where there is no data in the list
                    Toast.makeText(Hisab_Display_Activity.this, "No data to send", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }



    private void displayData() {
        sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + TABLENAME + "", null);
        courseModelArrayList = new ArrayList<Modelm>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String fnamee = cursor.getString(1);
            String motmeal = cursor.getString(2);
            String motjoma = cursor.getString(3);
            String mealRatesave = cursor.getString(4);
            String motkhoroj = cursor.getString(5);
            String managerpabe = cursor.getString(6);
            String managerdibe = cursor.getString(7);

            courseModelArrayList.add(new Modelm(id, fnamee, motmeal, motjoma, mealRatesave, motkhoroj, managerpabe, managerdibe));
        }
        cursor.close();

        adapter = new MyAdapterm(courseModelArrayList, Hisab_Display_Activity.this, sqLiteDatabase);
        recyclerView.setAdapter(adapter);
    }

    private void findId() {
        recyclerView = findViewById(R.id.rv);
        send = findViewById(R.id.saveimage);
    }


}
