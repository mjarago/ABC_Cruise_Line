package com.example.arago.abccompany;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.Layout;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.sql.SQLInput;
import java.util.ArrayList;
import java.util.List;

public class DBOperationSupport {
    private static SQLiteDatabase wdb = null;
    private static MyDB db = null;
    public static SQLiteDatabase getWritable(Context context) {
        if (db == null)
            db = new MyDB(context);

        if (wdb == null)
            wdb = db.getWritableDatabase();

        return wdb;

    }
    public static String getCustID(String selectQuery, SQLiteDatabase wdb){
        String custID = "";
        Cursor cursor = wdb.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                custID = cursor.getString(0);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return custID;
    }
    public  static String getCabinID(String selectQuery, SQLiteDatabase wdb){
        String cabinID = "";
        Cursor cursor = wdb.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                cabinID = cursor.getString(0);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return cabinID;
    }



    public static ArrayList<String> getAllLabels(String selectQuery,SQLiteDatabase wdb){
        ArrayList<String> str = new ArrayList<>();
        Cursor cursor = wdb.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{
                str.add(cursor.getString(0));
            }while (cursor.moveToNext());
        }

        cursor.close();

        return str;

    }


    public static void displayAll(TableLayout theView, SQLiteDatabase wdb, Context context, String selectQuery) {

        theView.removeAllViews(); // to remove previously created rows
        try {
            Cursor cursor = wdb.rawQuery(selectQuery, null); // 2nd arg is for where clause. For joining tables, don't use it

            String[] columnNames = cursor.getColumnNames();

            // looping through all rows and adding to list
            if (cursor != null) {
                cursor.moveToFirst();
                TextView data;
                TableRow row;

                // create the header
                row = new TableRow(context);
                for (int i=0 ; i < columnNames.length; i++) {
                    row.setPadding(2,2,2,2);
                    row.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    data = new TextView(context);
                    data.setTypeface(Typeface.DEFAULT_BOLD);
                    data.setText(columnNames[i]);
                    row.addView(data);
                }
                theView.addView(row);

                int cnt = 0 ;
                do {
                    row = new TableRow(context);      // creates 1 row at a time
                    // left, top, right, bottom
                    row.setPadding(2,2,2,2);

                    for (int x = 0; x < cursor.getColumnCount(); x++) {
                        data = new TextView(context);  // creates TextView dynamically
                        if (x == 0) {                           // variable x represents the column, x == 0 means first column
                            data.setTypeface(Typeface.DEFAULT_BOLD);
                            data.setGravity(Gravity.CENTER_HORIZONTAL);
                        }
                        data.setText(cursor.getString(x));
                        row.addView(data);                      // adds each column to the row
                    }
                    theView.addView(row);                       // adds the new row to the table
                } while (cursor.moveToNext());

                theView.setStretchAllColumns(true);
                cursor.close();
            }
        } catch (Exception ex) { }
    }

    public static void close() {
        if (db != null)
            db.close();
    }


}
