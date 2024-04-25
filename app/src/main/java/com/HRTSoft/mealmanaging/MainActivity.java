package com.HRTSoft.mealmanaging;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DBmain dBmain;
    private SQLiteDatabase sqLiteDatabase;
    private TextInputLayout fname, lname;
    private Button submit, display;
    private Spinner spinner;
    private int id = 0;
    FloatingActionButton Messhisab;


    DBmain dbHelper;

    RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dBmain = new DBmain(this);

        // Create method
        findId();
        insertData();

        dbHelper = new DBmain(this);
        findId();






    }

    private void findId() {
        fname = findViewById(R.id.edit_fname);
        lname = findViewById(R.id.edit_lname);
        submit = findViewById(R.id.submit_btn);
        display = findViewById(R.id.display_btn);
        recyclerView = findViewById(R.id.rv);

        spinner = findViewById(R.id.spinner);
        Messhisab=findViewById(R.id.Messhisabid);

        Messhisab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, Mess_Hisab_Activity.class);
                startActivity(intent);
            }
        });




    }

    private void insertData() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cv = new ContentValues();
                cv.put("fname", fname.getEditText().getText().toString());
                cv.put("lname", lname.getEditText().getText().toString());
                cv.put("spinner_value", spinner.getSelectedItem().toString());

                sqLiteDatabase = dBmain.getWritableDatabase();
                long recInsert = sqLiteDatabase.insert(DBmain.TABLENAME, null, cv);

                if (recInsert != -1) {
                    Toast.makeText(MainActivity.this, "Successfully inserted data", Toast.LENGTH_SHORT).show();
                    // Clear when clicking on submit
                    clearInputFields();
                } else {
                    Toast.makeText(MainActivity.this, "Something went wrong, please try again", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // When clicked on display button, open display data activity
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the Display_data_Activity
                startActivity(new Intent(MainActivity.this, Display_data_Activity.class));
            }
        });

        // Rest of your code...
    }





    private void clearInputFields() {
        fname.getEditText().setText("");
        lname.getEditText().setText("");
        // Clear spinner selection
        spinner.setSelection(0);
    }







}


