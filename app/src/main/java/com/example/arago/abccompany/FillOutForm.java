package com.example.arago.abccompany;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class FillOutForm extends AppCompatActivity {
    TableLayout theView;
    SQLiteDatabase wdb;
    String fromDate, toDate, title, shipName, roomType, pr, disp = "", poc = "";
    double price;
    int shipID;
    TextView tvRoomType, tvOnBoard, tvTitle, tvDate, tvPrice, tvPoc;
    EditText firstName, lastName, email, address;
    ArrayList<String> roomNumbers = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    ArrayList<String> onBoardAct = new ArrayList<>();
    ArrayList<String> portsOfCall = new ArrayList<>();
    String roomNumber = "999";
    Spinner spRoom;
    NumberFormat formatter = new DecimalFormat("#,###");

    Date today = Calendar.getInstance().getTime();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_out_form);

        //"CREATE TABLE CUSTOMER (cust_id integer primary key, firstName text, lastName text, address text, cabin_id integer,"
//                    + "FOREIGN KEY (cabin_id) REFERENCES CABIN(cabin_id) on delete cascade)";

        wdb = DBOperationSupport.getWritable(this);

        theView = (TableLayout) findViewById(R.id.theTable);
        Bundle myBundle = getIntent().getExtras();
        fromDate = myBundle.getString("FromDate");
        toDate = myBundle.getString("ToDate");
        title = myBundle.getString("Title");
        shipName = myBundle.getString("ShipName");
        roomType = myBundle.getString("RoomType");

        onBoardAct = myBundle.getStringArrayList("OnBoard");
        portsOfCall = myBundle.getStringArrayList("PortsOfCall");

        pr = myBundle.getString("Price");
        price = Double.parseDouble(pr.replaceAll("[^\\d.]", ""));

        //Select ALL Query


        if(shipName.equalsIgnoreCase("Northern Spirit")){
            shipID = 1;
        }
        else if(shipName.equalsIgnoreCase("Star Line")){
            shipID = 2;
        }
        else if(shipName.equalsIgnoreCase("Cheerio Cruise")){
            shipID = 3;
        }
        else{
            shipID = 4;
        }

        if(onBoardAct.size() == 0)
            disp = "None";
        else
            disp = "";
        for(int x = 0; x < onBoardAct.size(); x++){
            disp += onBoardAct.get(x);
            if(x<onBoardAct.size()-1)
                disp += ", ";
        }

        if(portsOfCall.size() == 0)
            poc = "None";
        else
            poc = "";
        for(int y = 0; y < portsOfCall.size(); y++){
            poc += portsOfCall.get(y);
            if(y < portsOfCall.size()-1)
                poc += ", ";
        }


        tvRoomType = (TextView) findViewById(R.id.txtRoomType);
        tvOnBoard = (TextView) findViewById(R.id.txtOnBoard);
        tvTitle = (TextView) findViewById(R.id.textTitle);
        tvDate = (TextView) findViewById(R.id.txtDate);
        tvPrice = (TextView) findViewById(R.id.txtPrice);
        tvPoc = (TextView) findViewById(R.id.txtPOC);

        tvRoomType.setText("");
        tvOnBoard.setText("");
        tvTitle.setText("");
        tvDate.setText("");
        tvPrice.setText("");
        tvPoc.setText("");
        tvRoomType.setText("Room Type: " + roomType);
        tvOnBoard.setText("On Board Activities: " + disp);
        tvDate.setText("Date: " + fromDate + " to " + toDate);
        tvPrice.setText("Price: $"+ (formatter.format(price)).toString());
        tvTitle.setText(title);
        tvPoc.setText("POC Activities: "  + poc);

        final String selectQuery = "SELECT oba_name, oba_limit FROM ONBOARD";
        //SPINNER ===================================
        final String selectQuery2 = "SELECT cabin_id, cabin_type, cabin_roomNum, ship_id, cabin_availability FROM CABIN WHERE cabin_type = '" + roomType + "'" +
                "AND cabin_availability = 'TRUE' AND ship_id = " + shipID;


        spRoom = (Spinner) findViewById(R.id.spinRoom);
        ArrayList<String> str = new ArrayList<>();
        str = DBOperationSupport.getAllLabels(selectQuery2,wdb);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, str);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spRoom.setAdapter(adapter);
        if(str.size() == 0){
            Toast.makeText(getApplicationContext(),"No More Available Rooms.",Toast.LENGTH_SHORT).show();
        }

        spRoom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                roomNumber = spRoom.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //=============================================
        //INSERT INTO CUSTOMER
        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstName = (EditText) findViewById(R.id.etFirstName);
                lastName  = (EditText) findViewById(R.id.etLastName);
                address = (EditText) findViewById(R.id.etAddress);
                email = (EditText) findViewById(R.id.etEmail);

                ContentValues values = new ContentValues();
                //Put values into customer Table
                values.put("firstName",firstName.getText().toString());
                values.put("lastName",lastName.getText().toString());
                values.put("address",address.getText().toString());
                values.put("email",email.getText().toString());
                values.put("cabin_id",Integer.parseInt(roomNumber));

                long newRowId = wdb.insert("CUSTOMER",null,values);

                //Disable some rooms
                wdb.execSQL("UPDATE CABIN " +
                        "SET cabin_availability = 'FALSE'" +
                        "WHERE cabin_id = " +  Integer.parseInt(roomNumber));
                //Reduce Limit for On Board Activities
                for(int x = 0; x < onBoardAct.size(); x++) {
                    wdb.execSQL("UPDATE ONBOARD " +
                            "SET oba_limit = oba_limit - 1 " +
                            "WHERE oba_name = '" + onBoardAct.get(x) +"'");
                }
                //reduce limit for ports of call activities
                for(int y = 0; y < portsOfCall.size(); y++){
                    wdb.execSQL("UPDATE PORTSOFCALL " +
                            "SET poc_limit = poc_limit - 1 " +
                            "WHERE activity_name = '" +portsOfCall.get(y) +"'");
                }

                //GET IDs and pass them all to booking ID
                String queryGetCustID = "SELECT cust_id FROM CUSTOMER " +
                        "WHERE lastName = '" + lastName.getText().toString() + "' AND " +
                        "cabin_id = " + Integer.parseInt(roomNumber);

                String custID = (DBOperationSupport.getCustID(queryGetCustID,wdb));


                String queryGetcabinID = "SELECT cabin_id FROM CUSTOMER " +
                        "WHERE lastName = '" + lastName.getText().toString() + "' AND " +
                        "cust_id = " + Integer.parseInt(custID);
                String cabinID = (DBOperationSupport.getCabinID(queryGetcabinID,wdb));

                ContentValues bookVal = new ContentValues();

                ArrayList<String> onBoardID = new ArrayList<>();
                ArrayList<String> portsOfCallID = new ArrayList<>();

                for(int x = 0; x < onBoardAct.size(); x++) {
                    String queryGetOBAID = "SELECT oba_id FROM ONBOARD " +
                            "WHERE oba_name like '%" + onBoardAct.get(x) + "%'";
                    String obID = (DBOperationSupport.getCustID(queryGetOBAID,wdb));
                    onBoardID.add(obID);
                    bookVal.put("cust_id",Integer.parseInt(custID));
                    bookVal.put("cabin_id",Integer.parseInt(cabinID));
                    bookVal.put("oba_id",Integer.parseInt(obID));
                    bookVal.put("booking_date",today.toString());

                    wdb.insert("BOOKING",null,bookVal);
                }

                ContentValues bookVal2 = new ContentValues();
                for(int y = 0; y < portsOfCall.size(); y++){
                    String queryGetPOCID = "SELECT poc_id FROM PORTSOFCALL " +
                            "WHERE activity_name  =  '" + portsOfCall.get(y) +"'";
                    String pocID = (DBOperationSupport.getCabinID(queryGetPOCID,wdb));
                    portsOfCallID.add(pocID);
                    bookVal2.put("cust_id",Integer.parseInt(custID));
                    bookVal2.put("cabin_id",Integer.parseInt(cabinID));
                    bookVal2.put("poc_id",Integer.parseInt(pocID));
                    bookVal2.put("booking_date",today.toString());

                    wdb.insert("BOOKING",null,bookVal2);
                }

                //insert cust_id and cabin_id into booking


                TextView test = (TextView) findViewById(R.id.test);
                //test
                String selectQuery = "SELECT cust_id, oba_id FROM BOOKING";

                DBOperationSupport.displayAll(theView,wdb,FillOutForm.this,selectQuery);
                Intent i = new Intent(FillOutForm.this,Invoice.class);
                i.putExtra("CruiseTitle",title);
                i.putExtra("ToDate",toDate);
                i.putExtra("FromDate",fromDate);
                i.putExtra("Price",price);
                i.putExtra("RoomType",roomType);
                i.putExtra("RoomNumber",roomNumber);
                i.putExtra("FirstName",firstName.getText().toString());
                i.putExtra("LastName",lastName.getText().toString());
                i.putExtra("ShipName",shipName);
                i.putExtra("cust_id", custID);
                portsOfCallID.clear();;
                onBoardID.clear();
                startActivity(i);

               // test.setText(disp +"cust-cabin" + obID);

            }
        });

    }

}
