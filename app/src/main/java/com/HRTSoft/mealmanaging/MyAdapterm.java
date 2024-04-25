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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapterm extends RecyclerView.Adapter<MyAdapterm.ModelViewHolder> {
    private ArrayList<Modelm> courseModelArrayList;
    private Context context;
    private SQLiteDatabase sqLiteDatabase;

    public MyAdapterm(ArrayList<Modelm> courseModelArrayList, Context context, SQLiteDatabase sqLiteDatabase) {
        this.courseModelArrayList = courseModelArrayList;
        this.context = context;
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @NonNull
    @Override
    public ModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.itemlayout, null);
        return new ModelViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ModelViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final Modelm model = courseModelArrayList.get(position);

        if (model != null) {
            holder.namem.setText(model.getFirstname());
            holder.motmeam.setText(model.getMotMeal());
            holder.jomam.setText(model.getMotJoma());
            holder.mealRatesave.setText(model.getMealRatesave());

            // Multiply motmeam and mealRatesave, then display the result in motkhoroj
            double motmeamValue = parseDoubleOrDefault(model.getMotMeal());
            double mealRatesaveValue = parseDoubleOrDefault(model.getMealRatesave());
            double motkhorojResult = motmeamValue * mealRatesaveValue;
            holder.motkhoroj.setText(String.valueOf(motkhorojResult));

            // Calculate the difference between jomam and motkhoroj
            double jomamValue = parseDoubleOrDefault(model.getMotJoma());
            double difference = jomamValue - motkhorojResult;

            // Compare the difference and set managerpabe and managerdibe accordingly
            if (difference > 0) {
                holder.managerpabe.setText("");
                holder.managerdibe.setText(String.valueOf(difference));
            } else {
                holder.managerpabe.setText(String.valueOf(Math.abs(difference) * 1));
                holder.managerdibe.setText("");
            }

            // Set up click listeners for item click
         }
    }

    private double parseDoubleOrDefault(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0.0; // or any default value you prefer
        }
    }

    @Override
    public int getItemCount() {
        return courseModelArrayList.size();
    }



    public class ModelViewHolder extends RecyclerView.ViewHolder {

        TextView namem, motmeam, jomam, mealRatesave,motkhoroj,managerpabe,managerdibe;
        ImageView edit, delete;

        public ModelViewHolder(@NonNull View itemView) {
            super(itemView);

            namem = itemView.findViewById(R.id.nameID);
            motmeam = itemView.findViewById(R.id.motmealID);
            jomam = itemView.findViewById(R.id.jomaID);
            mealRatesave = itemView.findViewById(R.id.mealRatesave);

            motkhoroj = itemView.findViewById(R.id.khorojID);
            managerpabe = itemView.findViewById(R.id.ManagerpabID);
            managerdibe = itemView.findViewById(R.id.ManagerdibeID);

        }
    }
}
