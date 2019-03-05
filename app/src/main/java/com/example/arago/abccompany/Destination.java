package com.example.arago.abccompany;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

public class Destination extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);

        final FrameLayout frame1 = (FrameLayout) findViewById(R.id.frame1);
        final FrameLayout frame2 = (FrameLayout) findViewById(R.id.frame2);
        final FrameLayout frame3 = (FrameLayout) findViewById(R.id.frame3);
        final FrameLayout frame4 = (FrameLayout) findViewById(R.id.frame4);

        frame1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Destination.this,location1.class);
                i.putExtra("Title","Northern Europe");
                i.putExtra("Background","location1");
                startActivity(i);
            }
        });

        frame2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Destination.this,location1.class);
                i.putExtra("Title","Mediterranean Cruise");
                i.putExtra("Background","location2");
                startActivity(i);
            }
        });

        frame3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Destination.this,location1.class);
                i.putExtra("Title","British Isles Cruise");
                i.putExtra("Background","location3");
                startActivity(i);
            }
        });

        frame4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Destination.this,location1.class);
                i.putExtra("Title","Barcelona");
                i.putExtra("Background","location4");
                startActivity(i);
            }
        });

    }
}
