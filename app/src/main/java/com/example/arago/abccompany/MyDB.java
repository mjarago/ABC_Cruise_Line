package com.example.arago.abccompany;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDB extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ABCCompany.db";

    public MyDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onOpen(SQLiteDatabase db){
        super.onOpen(db);
        if (!db.isReadOnly()){
            //Enable foreign key constraints
            db.execSQL("PRAGMA foreign_key = ON;");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //CREATE TABLES
        db.execSQL(TableDefinitions.SQL_CREATE_BOOKING);
        db.execSQL(TableDefinitions.SQL_CREATE_CABIN);
        db.execSQL(TableDefinitions.SQL_CREATE_CRUISE);
        db.execSQL(TableDefinitions.SQL_CREATE_CUSTOMER);
        db.execSQL(TableDefinitions.SQL_CREATE_INVOICE);
        db.execSQL(TableDefinitions.SQL_CREATE_ONBOARD);
        db.execSQL(TableDefinitions.SQL_CREATE_PORTSOFCALL);
        db.execSQL(TableDefinitions.SQL_CREATE_SHIP);

        //Fill tables
        db.execSQL(TableDefinitions.SQL_FILL_CABIN);
        db.execSQL(TableDefinitions.SQL_FILL_CRUISE);
        db.execSQL(TableDefinitions.SQL_FILL_ONBOARD);
        db.execSQL(TableDefinitions.SQL_FILL_SHIP);
        db.execSQL(TableDefinitions.SQL_FILL_PORTSOFCALL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TableDefinitions.SQL_DELETE_BOOKING);
        db.execSQL(TableDefinitions.SQL_DELETE_CABIN);
        db.execSQL(TableDefinitions.SQL_DELETE_CRUISE);
        db.execSQL(TableDefinitions.SQL_DELETE_CUSTOMER);
        db.execSQL(TableDefinitions.SQL_DELETE_INVOICE);
        db.execSQL(TableDefinitions.SQL_DELETE_ONBOARD);
        db.execSQL(TableDefinitions.SQL_DELETE_PORTSOFCALL);
        db.execSQL(TableDefinitions.SQL_DELETE_SHIP);
        Log.d("MyDB","OnUpgrade");
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("MyDB","onDowngrade");
        onUpgrade(db, oldVersion, newVersion);
    }
}
