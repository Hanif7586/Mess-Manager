package com.HRTSoft.mealmanaging;

import static com.HRTSoft.mealmanaging.DBmain.TABLENAME;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ModelViewHolder> {
    private ArrayList<Model> courseModelArrayList;
    private Context context;
    private SQLiteDatabase sqLiteDatabase;

    public MyAdapter(ArrayList<Model> courseModelArrayList, Context context, SQLiteDatabase sqLiteDatabase) {
        this.courseModelArrayList = courseModelArrayList;
        this.context = context;
        this.sqLiteDatabase = sqLiteDatabase;
    }

    public void filterList(ArrayList<Model> filterlist) {
        courseModelArrayList = filterlist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.singledata, null);
        return new ModelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ModelViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final Model model = courseModelArrayList.get(position);

        holder.txtfname.setText(model.getFirstname());
        holder.txtlname.setText(model.getLastname());


        // Set up the Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                context,
                R.array.on_off_options,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.spinner.setAdapter(adapter);

        // Set the selected item based on the value in the model
        int positionInArray = adapter.getPosition(model.getSpinnerValue());
        holder.spinner.setSelection(positionInArray);

        // Handle Spinner item selection
        holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Update the Spinner value in the model when an item is selected
                model.setSpinnerValue(parentView.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here if nothing is selected
            }
        });



        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", model.getId());
                bundle.putString("fname", model.getFirstname());
                bundle.putString("lname", model.getLastname());

                // Get the selected item from the Spinner
                String selectedSpinnerItem = holder.spinner.getSelectedItem().toString();
                bundle.putString("spinner_value", selectedSpinnerItem);

                Intent intent = new Intent(context, Edit_Activity.class);
                intent.putExtra("userdata", bundle);
                context.startActivity(intent);
            }
        });


        holder.delete.setOnClickListener(new View.OnClickListener() {
            DBmain dBmain = new DBmain(context);

            @Override
            public void onClick(View v) {
                sqLiteDatabase = dBmain.getReadableDatabase();
                long delete = sqLiteDatabase.delete(TABLENAME, "id=" + model.getId(), null);
                if (delete != -1) {
                    Toast.makeText(context, "Deleted data successfully", Toast.LENGTH_SHORT).show();
                    courseModelArrayList.remove(position);
                    notifyDataSetChanged();
                    // Update total amount after deletion
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return courseModelArrayList.size();
    }

    public class ModelViewHolder extends RecyclerView.ViewHolder {
        TextView txtfname, txtlname;
        ImageView edit, delete;
        CardView maincard;
        Spinner spinner; // Add Spinner here


        public ModelViewHolder(@NonNull View itemView) {
            super(itemView);

            txtfname = itemView.findViewById(R.id.txtfname);
            txtlname = itemView.findViewById(R.id.txtlname);


            edit = itemView.findViewById(R.id.txt_btn_edit);
            delete = itemView.findViewById(R.id.txt_btn_delete);
            maincard = itemView.findViewById(R.id.cardviewdatils);
            spinner = itemView.findViewById(R.id.spinner); // Initialize the Spinner here
        }
    }
}
