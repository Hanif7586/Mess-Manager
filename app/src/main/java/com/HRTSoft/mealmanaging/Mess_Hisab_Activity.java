package com.HRTSoft.mealmanaging;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class Mess_Hisab_Activity extends AppCompatActivity {

    DBmainm dBmainn;
    SQLiteDatabase sqLiteDatabase;
    TextInputLayout fname, motmeal, motjoma, mealRatesave;
    Button submit, display, edit;

    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess_hisab);

        dBmainn = new DBmainm(this);
        // Create method
        findid();
        insertData();
        editData();
    }

    private void editData() {
        if (getIntent().getBundleExtra("userdata") != null) {
            Bundle bundle = getIntent().getBundleExtra("userdata");
            id = bundle.getInt("id");

            fname.getEditText().setText(bundle.getString("fname"));
            motmeal.getEditText().setText(bundle.getString("motmeal"));
            motjoma.getEditText().setText(bundle.getString("motjoma"));
            mealRatesave.getEditText().setText(bundle.getString("mealRatesave"));


            edit.setVisibility(View.VISIBLE);
            submit.setVisibility(View.GONE);
        }
    }

    private void insertData() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cv = new ContentValues();
                cv.put("fname", fname.getEditText().getText().toString());
                cv.put("motmeal", motmeal.getEditText().getText().toString());
                cv.put("motjoma", motjoma.getEditText().getText().toString());
                cv.put("mealRatesave", mealRatesave.getEditText().getText().toString());


                sqLiteDatabase = dBmainn.getWritableDatabase();
                long recinsert = sqLiteDatabase.insert(DBmainm.TABLENAME, null, cv);
                if (recinsert != -1) {
                    Toast.makeText(Mess_Hisab_Activity.this, "Successfully inserted data", Toast.LENGTH_SHORT).show();
                    clearInputFields();
                } else {
                    Toast.makeText(Mess_Hisab_Activity.this, "Something went wrong, please try again", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // When clicked on display button, open display data activity
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add logic for opening display data activity
                Intent intent=new Intent(Mess_Hisab_Activity.this,Hisab_Display_Activity.class);
                startActivity(intent);

            }
        });

        // Storing edited data
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cv = new ContentValues();
                cv.put("fname", fname.getEditText().getText().toString());
                cv.put("motmeal", motmeal.getEditText().getText().toString());
                cv.put("motjoma", motjoma.getEditText().getText().toString());
                cv.put("mealRatesave", mealRatesave.getEditText().getText().toString());


                sqLiteDatabase = dBmainn.getReadableDatabase();
                long recedit = sqLiteDatabase.update(DBmainm.TABLENAME, cv, "id=" + id, null);
                if (recedit != -1) {
                    Toast.makeText(Mess_Hisab_Activity.this, "Data updated successfully", Toast.LENGTH_SHORT).show();
                    submit.setVisibility(View.VISIBLE);
                    edit.setVisibility(View.GONE);
                } else {
                    Toast.makeText(Mess_Hisab_Activity.this, "Something went wrong, please try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void findid() {
        fname = findViewById(R.id.nameID);
        motmeal = findViewById(R.id.mealID);
        motjoma = findViewById(R.id.jomaID);
        mealRatesave = findViewById(R.id.mealsaveID);

        submit = findViewById(R.id.save);
        display = findViewById(R.id.listcheckID);
        edit = findViewById(R.id.edit_btn);
    }

    private void clearInputFields() {
        fname.getEditText().setText("");
        motmeal.getEditText().setText("");
        motjoma.getEditText().setText("");
        mealRatesave.getEditText().setText("");

    }


}
