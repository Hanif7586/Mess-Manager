package com.HRTSoft.mealmanaging;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.widget.SearchView;

import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Display_data_Activity extends AppCompatActivity {
    DBmain dbHelper;
    SQLiteDatabase sqLiteDatabase;
    RecyclerView recyclerView;
    MyAdapter adapter;
    SearchView searchView;
    private ArrayList<Model> courseModelArrayList;
    TextView itemCountTextView;
    private Button generatePDFButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);

        dbHelper = new DBmain(this);
        findId();
        displayData();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        searchView = findViewById(R.id.searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });

        // Add a button to trigger PDF generation


    }






    private void filter(String text) {
        ArrayList<Model> filteredList = new ArrayList<>();

        for (Model item : courseModelArrayList) {
            String lowercaseText = text.toLowerCase().trim();
            if (
                    item.getLastname().toLowerCase().contains(lowercaseText) ||
                            item.getSpinnerValue().toLowerCase().contains(lowercaseText)) {
                filteredList.add(item);
            }
        }

        if (filteredList.isEmpty()) {
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {
            adapter.filterList(filteredList);
            // Update item count TextView
            int onCount = countSpinnerValueOccurrences(filteredList, "ON");
            int offCount = countSpinnerValueOccurrences(filteredList, "OFF");
            itemCountTextView.setText("Item Count: " + filteredList.size() + ", ON Count: " + onCount + ", OFF Count: " + offCount);
        }
    }

    private void displayData() {
        sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + DBmain.TABLENAME + "", null);
        courseModelArrayList = new ArrayList<>();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String fname = cursor.getString(1);
            String lnumber = cursor.getString(2);
            String spinnerValue = cursor.getString(3);

            courseModelArrayList.add(new Model(id, fname, lnumber, spinnerValue));
        }

        cursor.close();
        adapter = new MyAdapter(courseModelArrayList, Display_data_Activity.this, sqLiteDatabase);
        recyclerView.setAdapter(adapter);

        // Count occurrences of <item>ON</item> and <item>OFF</item> and update item count TextView
        int onCount = countSpinnerValueOccurrences(courseModelArrayList, "ON");
        int offCount = countSpinnerValueOccurrences(courseModelArrayList, "OFF");
        itemCountTextView.setText("Members: " + courseModelArrayList.size() + ", Meal ON : " + onCount + ",Meal OFF: " + offCount);
    }

    private int countSpinnerValueOccurrences(ArrayList<Model> list, String valueToCount) {
        int count = 0;
        for (Model item : list) {
            if (item.getSpinnerValue().equalsIgnoreCase(valueToCount)) {
                count++;
            }
        }
        return count;
    }

    private void findId() {
        recyclerView = findViewById(R.id.rv);
        itemCountTextView = findViewById(R.id.itemCountTextView);

    }
}
