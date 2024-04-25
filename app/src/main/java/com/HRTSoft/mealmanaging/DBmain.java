package com.HRTSoft.mealmanaging;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBmain extends SQLiteOpenHelper {
    public static final String DBNAME = "aak.db";
    public static final String TABLENAME = "course";
    public static final int VER = 2; // Increase the version to trigger onUpgrade
    String query;

    // New column for the Spinner selection
    public static final String COLUMN_SPINNER_VALUE = "spinner_value";

    public DBmain(@Nullable Context context) {
        super(context, DBNAME, null, VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Update the query to include the new column
        query = "create table " + TABLENAME + "(id integer primary key, fname text, lname text, "
                + COLUMN_SPINNER_VALUE + " text)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the existing table and recreate it
        query = "drop table if exists " + TABLENAME + "";
        db.execSQL(query);
        onCreate(db);
    }
}
