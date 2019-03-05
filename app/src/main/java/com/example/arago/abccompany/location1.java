package com.example.arago.abccompany;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class location1 extends AppCompatActivity {
    Button btnItinerary, btnOnBoard, btnDecks, btnPorts;
    String title, background;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location1);
        TextView textView = (TextView) findViewById(R.id.title);
        ImageView bgImage = (ImageView) findViewById(R.id.bgImage);

        Bundle myBundle = getIntent().getExtras();

        title = myBundle.getString("Title");
        background = myBundle.getString("Background");

        textView.setText(title);

        if(background.equals("location1")){
            bgImage.setImageResource(R.drawable.disney1);
        }
        if(background.equals("location2")){
            bgImage.setImageResource(R.drawable.disney2);
        }
        if(background.equals("location3")){
            bgImage.setImageResource(R.drawable.disney3);
        }
        if(background.equals("location4")){
            bgImage.setImageResource(R.drawable.disney4);
        }

        btnItinerary = (Button)findViewById(R.id.btnItinerary);

        btnItinerary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(location1.this,Itinerary1.class);
                i.putExtra("Title",title);
                i.putExtra("Background",background);
                startActivity(i);
            }
        });

        btnOnBoard = (Button) findViewById(R.id.btnOBA);

        btnOnBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(location1.this,OnBoard.class);
                i.putExtra("Title",title);
                i.putExtra("Background",background);
                startActivity(i);
            }
        });

        btnDecks = (Button) findViewById(R.id.btnDecks);
        btnDecks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii = new Intent(location1.this, deckLayout.class);
                ii.putExtra("Title",title);
                ii.putExtra("Background",background);
                startActivity(ii);
            }
        });

        btnPorts = (Button) findViewById(R.id.btnPOC);
        btnPorts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(location1.this, PortsOfCall.class);
                i.putExtra("Title",title);
                i.putExtra("Background",background);
                startActivity(i);
            }
        });


    }
}
