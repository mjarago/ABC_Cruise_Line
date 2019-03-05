package com.example.arago.abccompany;

import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Invoice extends AppCompatActivity {
    TextView invCruiseTitle, invDate, invShip, invPrice, invRoomType, invName;
    String fName, lName, rmNumber, rmType, pr, fromDate, toDate, cruiseTitle, ship, custID;
    Double price;
    TableLayout theView, theView2;
    SQLiteDatabase wdb;

    NumberFormat formatter = new DecimalFormat("#,###");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);

        wdb = DBOperationSupport.getWritable(this);

        invCruiseTitle = (TextView) findViewById(R.id.invCruiseTitle);
        invDate = (TextView) findViewById(R.id.invDate);
        invShip = (TextView) findViewById(R.id.invShip);
        invPrice = (TextView) findViewById(R.id.invPrice);
        invRoomType = (TextView) findViewById(R.id.invRoomType);
        invName = (TextView) findViewById(R.id.invName);


        Bundle myBundle = getIntent().getExtras();
        cruiseTitle = myBundle.getString("CruiseTitle");
        toDate = myBundle.getString("ToDate");
        fromDate = myBundle.getString("FromDate");
        price = myBundle.getDouble("Price");
        rmType = myBundle.getString("RoomType");
        rmNumber = myBundle.getString("RoomNumber");
        fName = myBundle.getString("FirstName");
        lName = myBundle.getString("LastName");
        ship = myBundle.getString("ShipName");
        custID = myBundle.getString("cust_id");
        int customerID = Integer.parseInt(custID);
        invName.setText("Invoice for " + fName + " " + lName);
        invCruiseTitle.setText(cruiseTitle);
        invDate.setText(fromDate + " to " + toDate);
        invPrice.setText(formatter.format(price).toString());
        invRoomType.setText(rmType + "(" +rmNumber +")");
        invShip.setText(ship);

        TextView id = (TextView) findViewById(R.id.id);
        id.setText(custID);
        //Enter Select Query for the invoice.
//        String selectQuery = "SELECT * FROM BOOKING";
//        theView = (TableLayout) findViewById(R.id.theTable);
//        DBOperationSupport.displayAll(theView,wdb,Invoice.this,selectQuery);
        String showOnBoard = ("SELECT * FROM BOOKING WHERE cust_id = " + customerID);
        theView = (TableLayout) findViewById(R.id.onboardTable);
        theView2 = (TableLayout) findViewById(R.id.tb2);
        //show OnBoard
        DBOperationSupport.displayAll(theView,wdb,Invoice.this,showOnBoard);

        String show2 = "SELECT oba_name, oba_price FROM ONBOARD, BOOKING " +
                "WHERE ONBOARD.oba_id = BOOKING.oba_id AND cust_id = " + customerID;
        DBOperationSupport.displayAll(theView2,wdb,Invoice.this,show2);

    }
}
