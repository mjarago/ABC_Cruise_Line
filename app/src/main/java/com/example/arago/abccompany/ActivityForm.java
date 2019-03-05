package com.example.arago.abccompany;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class    ActivityForm extends AppCompatActivity {
    Double price;
    String rmType,shipName,Title,fromDate,toDate,price2;
    String pr, location;
    CheckBox spa, imax, goKart, painting;
    ArrayList<String> onBoardAct = new ArrayList<>();
    ArrayList<String> portsOfCallAct = new ArrayList<>();
    //========================
    //ports of call activities values and components
    String cruiseCity;
    int cost;
    TextView tvPort1,tvPort2,tvPort3;
    CheckBox ckbActiv1,ckbActiv2,ckbActiv3,ckbActiv4,ckbActiv5,ckbActiv6;

    //Copenhagen data
    String ports1[] = {"Berlin","St PeterBurg","Helsinki"};
    String activities1[] = {"Brandenburg Gate","Potsdamer Platz",
            "Hermitage","Peterhof Palace",
            "Suomenlinna","Market Square"};
    int prices1[] = {50,75,25,100,50,25};
    //Rome data
    String ports2[] = {"Koper","Split","Corfu"};
    String activities2[] = {"Praetorian Palace","Tito Square",
            "Trajektna Luka","Diocletian Palace",
            "Sidari","Achilleion"};
    int prices2[] = {50,175,25,60,50,45};
    //Liverpool data
    String ports3[] = {"Edinburg","Dublin"};
    String activities3[] = {"Edinburg Castle","Old Town",
            "Gunnies House","Dublin Castle"};
    int prices3[] = {150,85,55,100};
    //Barcelona data
    String ports4[] = {"Naples","Cannes"};
    String activities4[] = {"Royal Place","Molo Beverello",
            "Lerins Islands","Ile Marguerite"};
    int prices4[] = {30,75,125,70};



    //
    TextView tvTitle, tvPrice, tvDate, tvRoomType;
    NumberFormat formatter = new DecimalFormat("#,###");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        /*
        i.putExtra("FromDate",from);
        i.putExtra("ToDate",to);
        i.putExtra("Title",roomTitle.getText().toString());
        i.putExtra("Price",totalPrice);
        i.putExtra("ShipName",shipName.getText().toString());
        i.putExtra("RoomType",rmType);
        i.putExtra("Location",background);
         */

        //get things from my bundle
         Bundle myBundle = getIntent().getExtras();
        assert myBundle != null;
        fromDate = myBundle.getString("FromDate");
        toDate = myBundle.getString("ToDate");
        Title = myBundle.getString("Title");
        shipName = myBundle.getString("ShipName");
        rmType = myBundle.getString("RoomType");
        location = myBundle.getString("Location:");
        price2 = myBundle.getString("Price2");

        price = Double.parseDouble(price2.replaceAll("[^\\d.]", ""));

        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvPrice = (TextView) findViewById(R.id.tvPrice);
        tvDate  = (TextView) findViewById(R.id.tvDate);
        tvRoomType = (TextView) findViewById(R.id.tvRoomType);

        spa = (CheckBox) findViewById(R.id.cbtnSpa);
        imax = (CheckBox) findViewById(R.id.cbtnImax);
        goKart = (CheckBox) findViewById(R.id.cbtnGoKart);
        painting = (CheckBox) findViewById(R.id.cbtnPainting);

        tvTitle.setText(Title);
        tvPrice.setText("Price: $"+ (formatter.format(price)).toString());
        tvDate.setText("Date: " + fromDate + " to " + toDate);
        tvRoomType.setText("Room Type: " + rmType);

        spa.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    price += 50;
                    tvPrice.setText("Price: $"+ (formatter.format(price)).toString());
                }
                else{
                    price -= 50;
                    tvPrice.setText("Price: $"+ (formatter.format(price)).toString());

                }
            }
        });
        imax.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    price += 50;
                    tvPrice.setText("Price: $"+ (formatter.format(price)).toString());
                }
                else{
                    price -= 50;
                    tvPrice.setText("Price: $"+ (formatter.format(price)).toString());
                }
            }
        });
        goKart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    price += 100;
                    tvPrice.setText("Price: $"+ (formatter.format(price)).toString());
                }
                else{
                    price -= 100;
                    tvPrice.setText("Price: $"+ (formatter.format(price)).toString());
                }
            }
        });
        painting.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    price += 200;
                    tvPrice.setText("Price: $"+ (formatter.format(price)).toString());
                }
                else{
                    price -= 200;
                    tvPrice.setText("Price: $"+ (formatter.format(price)).toString());
                }
            }
        });

        //PORTS OF CALL
        //ports of call activities components instantiation
        tvPort1 = (TextView) findViewById(R.id.port1Name);
        tvPort2 = (TextView) findViewById(R.id.port2Name);
        tvPort3 = (TextView) findViewById(R.id.port3Name);
        ckbActiv1 = (CheckBox) findViewById(R.id.ckbActiv1);
        ckbActiv2 = (CheckBox) findViewById(R.id.ckbActiv2);
        ckbActiv3 = (CheckBox) findViewById(R.id.ckbActiv3);
        ckbActiv4 = (CheckBox) findViewById(R.id.ckbActiv4);
        ckbActiv5 = (CheckBox) findViewById(R.id.ckbActiv5);
        ckbActiv6 = (CheckBox) findViewById(R.id.ckbActiv6);
        //define the active cruise departure city
        cruiseCity = tvTitle.getText().toString();
        cruiseCity = cruiseCity.substring(cruiseCity.lastIndexOf(' ')+1,cruiseCity.length());

        switch (cruiseCity) {
            case "Copenhagen":
                tvPort1.setText(ports1[0]);
                tvPort2.setText(ports1[1]);
                tvPort3.setText(ports1[2]);
                ckbActiv1.setText(activities1[0]);
                ckbActiv2.setText(activities1[1]);
                ckbActiv3.setText(activities1[2]);
                ckbActiv4.setText(activities1[3]);
                ckbActiv5.setText(activities1[4]);
                ckbActiv6.setText(activities1[5]);
                break;
            case "Rome":
                tvPort1.setText(ports2[0]);
                tvPort2.setText(ports2[1]);
                tvPort3.setText(ports2[2]);
                ckbActiv1.setText(activities2[0]);
                ckbActiv2.setText(activities2[1]);
                ckbActiv3.setText(activities2[2]);
                ckbActiv4.setText(activities2[3]);
                ckbActiv5.setText(activities2[4]);
                ckbActiv6.setText(activities2[5]);
                break;
            case "Liverpool":
                tvPort1.setText(ports3[0]);
                tvPort2.setText(ports3[1]);
                tvPort3.setVisibility(View.INVISIBLE);
                ckbActiv1.setText(activities3[0]);
                ckbActiv2.setText(activities3[1]);
                ckbActiv3.setText(activities3[2]);
                ckbActiv4.setText(activities3[3]);
                ckbActiv5.setVisibility(View.INVISIBLE);
                ckbActiv6.setVisibility(View.INVISIBLE);
                break;
            case "Barcelona":
                tvPort1.setText(ports4[0]);
                tvPort2.setText(ports4[1]);
                tvPort3.setVisibility(View.INVISIBLE);
                ckbActiv1.setText(activities4[0]);
                ckbActiv2.setText(activities4[1]);
                ckbActiv3.setText(activities4[2]);
                ckbActiv4.setText(activities4[3]);
                ckbActiv5.setVisibility(View.INVISIBLE);
                ckbActiv6.setVisibility(View.INVISIBLE);
                break;
        }

        //methods charging on base price based on activities selection
        ckbActiv1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switch (cruiseCity) {
                    case "Copenhagen": cost = prices1[0]; break;
                    case "Rome": cost = prices2[0]; break;
                    case "Liverpool": cost = prices3[0]; break;
                    case "Barcelona": cost = prices4[0]; break;
                    default: cost = 0;
                }
                if(isChecked) {price += cost;} else {price -= cost;}
                tvPrice.setText("Price: $"+ (formatter.format(price)).toString());
            }
        });

        ckbActiv2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switch (cruiseCity) {
                    case "Copenhagen": cost = prices1[1]; break;
                    case "Rome": cost = prices2[1]; break;
                    case "Liverpool": cost = prices3[1]; break;
                    case "Barcelona": cost = prices4[1]; break;
                    default: cost = 0;
                }
                if(isChecked) {price += cost;} else {price -= cost;}
                tvPrice.setText("Price: $"+ (formatter.format(price)).toString());
            }
        });

        ckbActiv3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switch (cruiseCity) {
                    case "Copenhagen": cost = prices1[2]; break;
                    case "Rome": cost = prices2[2]; break;
                    case "Liverpool": cost = prices3[2]; break;
                    case "Barcelona": cost = prices4[2]; break;
                    default: cost = 0;
                }
                if(isChecked) {price += cost;} else {price -= cost;}
                tvPrice.setText("Price: $"+ (formatter.format(price)).toString());
            }
        });

        ckbActiv4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switch (cruiseCity) {
                    case "Copenhagen": cost = prices1[3]; break;
                    case "Rome": cost = prices2[3]; break;
                    case "Liverpool": cost = prices3[3]; break;
                    case "Barcelona": cost = prices4[3]; break;
                    default: cost = 0;
                }
                if(isChecked) {price += cost;} else {price -= cost;}
                tvPrice.setText("Price: $"+ (formatter.format(price)).toString());
            }
        });

        ckbActiv5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switch (cruiseCity) {
                    case "Copenhagen": cost = prices1[4]; break;
                    case "Rome": cost = prices2[4]; break;
                    default: cost = 0;
                }
                if(isChecked) {price += cost;} else {price -= cost;}
                tvPrice.setText("Price: $"+ (formatter.format(price)).toString());
            }
        });

        ckbActiv6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switch (cruiseCity) {
                    case "Copenhagen": cost = prices1[5]; break;
                    case "Rome": cost = prices2[5]; break;
                    default: cost = 0;
                }
                if(isChecked) {price += cost;} else {price -= cost;}
                tvPrice.setText("Price: $"+ (formatter.format(price)).toString());
            }
        });


        Button nextButton = (Button) findViewById(R.id.btnNext);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBoardAct.clear();
                portsOfCallAct.clear();

                if(spa.isChecked())
                    onBoardAct.add("Spa");
                if(imax.isChecked())
                    onBoardAct.add("Imax");
                if(goKart.isChecked())
                    onBoardAct.add("GoKart");
                if(painting.isChecked())
                    onBoardAct.add("Painting");

                if(ckbActiv1.isChecked())
                    portsOfCallAct.add(ckbActiv1.getText().toString());
                if(ckbActiv2.isChecked())
                    portsOfCallAct.add(ckbActiv2.getText().toString());
                if(ckbActiv3.isChecked())
                    portsOfCallAct.add(ckbActiv3.getText().toString());
                if(ckbActiv4.isChecked())
                    portsOfCallAct.add(ckbActiv4.getText().toString());
                if(ckbActiv5.isChecked())
                    portsOfCallAct.add(ckbActiv5.getText().toString());
                if(ckbActiv6.isChecked())
                    portsOfCallAct.add(ckbActiv6.getText().toString());


                Intent i = new Intent(ActivityForm.this,FillOutForm.class);

                i.putExtra("FromDate",fromDate);
                i.putExtra("ToDate",toDate);
                i.putExtra("Title",Title);
                i.putExtra("ShipName",shipName);
                i.putExtra("RoomType",rmType);
                i.putExtra("Price",price.toString());
                i.putStringArrayListExtra("OnBoard",onBoardAct);
                i.putStringArrayListExtra("PortsOfCall",portsOfCallAct);
                startActivity(i);
            }
        });

    }


}