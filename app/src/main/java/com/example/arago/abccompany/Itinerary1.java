package com.example.arago.abccompany;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Itinerary1 extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    Button datePicker, btnReserve, viewPOC;
    TextView textView, deptDate, cruiseTitle, cruiseDesc, shipName;
    ImageView bgImage;
    //For Spinner / Room Type----------
    Spinner spinnerRmType;
    TextView roomTitle, roomDesc, roomPrice;
    ImageView roomImage;
    float totalPrice; // Price including the room (base price + room price)
    String rmType;
    //---------------------------------
    String title, background, date;
    int DAYS = 5; //NUMBER OF DAYS EITHER 5/7
    String from = "",to = ""; //FROM = DEPARTURE/SELECTED DATE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itinerary1);

        textView = (TextView) findViewById(R.id.title);
        bgImage = (ImageView) findViewById(R.id.bgImage);
        cruiseTitle = (TextView) findViewById(R.id.cruiseTitle);
        cruiseDesc = (TextView) findViewById(R.id.cruiseDesc);
        shipName = (TextView) findViewById(R.id.shipName);

        Bundle myBundle = getIntent().getExtras();

        title = myBundle.getString("Title");
        background = myBundle.getString("Background");
        textView.setText(title);

        if(background.equals("location1")){ // northern europe
            bgImage.setImageResource(R.drawable.disney1);
            DAYS = 7;
            cruiseTitle.setText(R.string.northern_europe_title);
            cruiseDesc.setText(R.string.northern_europe_desc);
            shipName.setText(R.string.northern_europe_ship);
        }
        if(background.equals("location2")){ // mediterr
            bgImage.setImageResource(R.drawable.disney2);
            DAYS = 7;
            cruiseTitle.setText(R.string.mediterr_cruise_title);
            cruiseDesc.setText(R.string.mediterr_cruise_desc);
            shipName.setText(R.string.mediterr_cruise_ship);
        }
        if(background.equals("location3")){ // british isles
            bgImage.setImageResource(R.drawable.disney3);
            DAYS = 5;
            cruiseTitle.setText(R.string.british_isles_title);
            cruiseDesc.setText(R.string.british_isles_desc);
            shipName.setText(R.string.british_isles_ship);
        }
        if(background.equals("location4")){ // barcelona
            bgImage.setImageResource(R.drawable.disney4);
            DAYS = 5;
            cruiseTitle.setText(R.string.barcelona_cruise_title);
            cruiseDesc.setText(R.string.barcelona_cruise_desc);
            shipName.setText(R.string.barcelona_cruise_ship);
        }
        //DATE PICKER
        datePicker = (Button) findViewById(R.id.btnSearch);
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                Calendar[] selectableDays = new Calendar[50];

                for(int x  = 0; x < 50; x++){
                    Calendar day = Calendar.getInstance();
                    day.add(Calendar.DAY_OF_WEEK, x * 14);
                    day.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
                    selectableDays[x] = day;
                }
                DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(Itinerary1.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.setTitle("Select Date");
                datePickerDialog.setMinDate(Calendar.getInstance());
                datePickerDialog.setSelectableDays(selectableDays);
                datePickerDialog.setThemeDark(true);
                datePickerDialog.show(getFragmentManager(),"Date Picker");
            }
        });//end of datepicker button


        //FOR SPINNER / ROOM TYPE ---------------------------------------------------------
        spinnerRmType = (Spinner) findViewById(R.id.spinRmType);
        roomTitle = (TextView) findViewById(R.id.roomTitle);
        roomDesc = (TextView) findViewById(R.id.roomDesc);
        roomPrice = (TextView) findViewById(R.id.roomPrice);
        roomImage = (ImageView) findViewById(R.id.roomImage);
        spinnerRmType.setSelection(0);//
        spinnerRmType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){//inside
                    roomTitle.setText(R.string.inside_title);
                    roomDesc.setText(R.string.inside_details);
                    roomImage.setImageResource(R.drawable.inside);
                    roomPrice.setText("$2,597");
                    totalPrice = 2597;
                    rmType = "Inside";
                }
                else if(position == 1){//oceanview
                    roomTitle.setText(R.string.oceanview_title);
                    roomDesc.setText(R.string.oceanview_details);
                    roomImage.setImageResource(R.drawable.oceanview);
                    roomPrice.setText("$2,808");
                    totalPrice = 2808;
                    rmType = "Oceanview";
                }
                else if(position == 2){//verandah
                    roomTitle.setText(R.string.verandah_title);
                    roomDesc.setText(R.string.verandah_details);
                    roomImage.setImageResource(R.drawable.verandah);
                    roomPrice.setText("$3,250");
                    totalPrice = 3250;
                    rmType = "Verandah";
                }
                else{//concierge
                    roomTitle.setText(R.string.concierge_title);
                    roomDesc.setText(R.string.concierge_details);
                    roomImage.setImageResource(R.drawable.concierge);
                    roomPrice.setText("$3,500");
                    totalPrice = 3500;
                    rmType = "Concierge";

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        //----------------------------------------------------------------------------------
        btnReserve = (Button) findViewById(R.id.btnReserve);
        btnReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Need:
                1. Date
                2. Title
                3. Price
                4. ShipName
                5. RoomType
                 */
                if(from.equals("")){
                    Toast.makeText(getApplicationContext(),"Select Date from the Search Button",Toast.LENGTH_SHORT).show();
                }
                else{//Move on to next form
                    Intent i = new Intent(Itinerary1.this,ActivityForm.class);
                    i.putExtra("FromDate",from);
                    i.putExtra("ToDate",to);
                    i.putExtra("Title",cruiseTitle.getText().toString());
                    i.putExtra("Price",roomPrice.getText().toString());
                    i.putExtra("Price2",roomPrice.getText().toString());
                    i.putExtra("ShipName",shipName.getText().toString());
                    i.putExtra("RoomType",rmType);
                    i.putExtra("location",background);
                    startActivity(i);
                }

            }
        });//end of button reserve

        viewPOC = (Button) findViewById(R.id.viewPOC);

        viewPOC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Itinerary1.this,PortsOfCall.class);
                i.putExtra("Title",title);
                i.putExtra("Background",background);
            }
        });



    }//end of Itinerary1 CLass

    //DATE PICKER ----- Outer Method
    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        deptDate = (TextView) findViewById(R.id.tvDate);
        Date date = new Date();

        String myString = (dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            date = sdf.parse(myString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(myString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //FROM = DEPARTURE DATE
        from = sdf.format(date);
        c.add(Calendar.DATE,DAYS);
        to = sdf.format(c.getTime());

        deptDate.setText("Date: " + from + " to " + to);
    }

}
