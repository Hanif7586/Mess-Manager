package com.HRTSoft.mealmanaging;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBmainm extends SQLiteOpenHelper {
    public static final String DBNAME = "hhk.db";
    public static final String TABLENAME = "courses";
    public static final int VER = 2; // Increment the version number

    public DBmainm(Context context) {
        super(context, DBNAME, null, VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table " + TABLENAME +
                "(id integer primary key, fname text, motmeal text, motjoma text, " +
                "mealRatesave text, motkhoroj text, managerpabe text, managerdibe text)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "drop table if exists " + TABLENAME;
        db.execSQL(query);
        onCreate(db);
    }
}