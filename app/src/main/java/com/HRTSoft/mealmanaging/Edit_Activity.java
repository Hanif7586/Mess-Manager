package com.HRTSoft.mealmanaging;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class Edit_Activity extends AppCompatActivity {

    private TextInputLayout fname, lname;
    private Spinner spinner;
    private Button edit;

    private int id; // Add the id variable
    private SQLiteDatabase sqLiteDatabase; // Add the sqLiteDatabase variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        fname = findViewById(R.id.edit_fname);
        lname = findViewById(R.id.edit_lname);
        spinner = findViewById(R.id.spinner);
        edit = findViewById(R.id.edit_btn);

        findId();
        editData();
    }

    private void findId() {
        // Populate the Spinner with options
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.on_off_options,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void editData() {
        if (getIntent().getBundleExtra("userdata") != null) {
            Bundle bundle = getIntent().getBundleExtra("userdata");
            id = bundle.getInt("id");

            // Access the EditText fields inside the TextInputLayout and set their text
            fname.getEditText().setText(bundle.getString("fname"));
            lname.getEditText().setText(bundle.getString("lname"));

            // Set the selected item in the spinner
            String selectedSpinnerValue = bundle.getString("spinner_value");
            if (selectedSpinnerValue != null) {
                int position = ArrayAdapter.createFromResource(
                        Edit_Activity.this,
                        R.array.on_off_options,
                        android.R.layout.simple_spinner_item
                ).getPosition(selectedSpinnerValue);

                if (position != -1) {
                    spinner.setSelection(position);
                }
            }
        }

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cv = new ContentValues();
                cv.put("fname", fname.getEditText().getText().toString());
                cv.put("lname", lname.getEditText().getText().toString());
                cv.put("spinner_value", spinner.getSelectedItem().toString());

                clearInputFields();

                DBmain dBmain = new DBmain(Edit_Activity.this);
                sqLiteDatabase = dBmain.getWritableDatabase();

                // Correct the database update statement
                long recEdit = sqLiteDatabase.update(
                        DBmain.TABLENAME,
                        cv,
                        "id=" + id,  // Assuming "id" is the primary key column name
                        null);

                if (recEdit != -1) {
                    Toast.makeText(Edit_Activity.this, "Data updated successfully", Toast.LENGTH_SHORT).show();

                    // Create an Intent to go back to MainActivity
                    Intent intent = new Intent(Edit_Activity.this, MainActivity.class);
                    startActivity(intent);

                    // Finish the current activity to prevent it from staying in the stack
                    finish();
                } else {
                    Toast.makeText(Edit_Activity.this, "Something went wrong, please try again", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void clearInputFields() {
        fname.getEditText().setText("");
        lname.getEditText().setText("");
        // Clear spinner selection
        spinner.setSelection(0);
    }
}
