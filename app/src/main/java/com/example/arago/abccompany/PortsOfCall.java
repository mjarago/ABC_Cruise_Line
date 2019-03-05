package com.example.arago.abccompany;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class PortsOfCall extends AppCompatActivity {
    String title, background;


    TextView port1, port2, port3, port1Act1, port1Act2, port2Act1, port2Act2, port3Act1, port3Act2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ports_of_call);

        ImageView bgImage = (ImageView) findViewById(R.id.pocImage);
        TextView textTitle = (TextView) findViewById(R.id.textTitle);

        Bundle myBundle = getIntent().getExtras();
        title = myBundle.getString("Title");
        background = myBundle.getString("Background");


        port1 = (TextView) findViewById(R.id.port1);
        port2 = (TextView) findViewById(R.id.port2);
        port3 = (TextView) findViewById(R.id.port3);
        port1Act1 = (TextView) findViewById(R.id.port1Act1);
        port1Act2 = (TextView) findViewById(R.id.port1Act2);
        port2Act1 = (TextView) findViewById(R.id.port2Act1);
        port2Act2 = (TextView) findViewById(R.id.port2Act2);
        port3Act1 = (TextView) findViewById(R.id.port3Act1);
        port3Act2 = (TextView) findViewById(R.id.port3Act2);



        if(background.equals("location1")){ // northern europe
            bgImage.setImageResource(R.drawable.disney1);
            port1.setText("Berlin");
            port2.setText("St Petersburg");
            port3.setText("Helsinki");
            port1Act1.setText("Brandenburg Gate - Enjoy the 18th-century neoclassical monument in Berlin. ($50)");
            port1Act2.setText("Potsdamer Platz in Berlin is a shopping and nightlife district with shops, restaurants, cinemas and theatres. ($ 75)");
            port2Act1.setText("The State Hermitage Museum is a museum of art and culture in Saint Petersburg ($ 25)");
            port2Act2.setText("The Peterhof Palace - Enjoy a series of palaces and gardens located in Petergof, Saint Petersburg. ($ 100)");
            port3Act1.setText("Suomenlinna is an 18th-century sea fortress and nature area with centuries-old artillery and defensive walls, spread across 6 linked islands.($ 50)");
            port3Act2.setText("The Market Square is a central square in Helsinki, Finland. ($ 50)");
        }
        if(background.equals("location2")){ // mediterr
            bgImage.setImageResource(R.drawable.disney2);
            port1.setText("Koper");
            port2.setText("Split");
            port3.setText("Korfu");
            port1Act1.setText("The Praetorian Palace is a 15th-century Venetian Gothic palace in the city of Koper, in southwest Slovenia. ($50)");
            port1Act2.setText("Tito square was very open, with a large statue in the middle of Tito, Koper. ($ 175)");
            port2Act1.setText("The port was originally a trading post originally established by Greek settlers from the island of Vis and subsequently taken over by the Romans.($60)");
            port2Act2.setText("Diocletian's Palace is an ancient palace built for the Roman Emperor Diocletian at the turn of the fourth century AD. ($ 50)");
            port3Act1.setText("Sidari is a settlement in the northern part of the island of Corfu. ($50)");
            port3Act2.setText("Achilleion is a palace built in Gastouri on the Island of Corfu by Empress of Austria, Elisabeth of Bavaria, also known as Sisi ($45)");


        }
        if(background.equals("location3")){ // british isles
            bgImage.setImageResource(R.drawable.disney3);
            port1.setText("Edinburgh");
            port2.setText("Dublin");
            port1Act1.setText("Edinburgh Castle is a historic fortress which dominates the skyline of the city of Edinburgh, Scotland, from its position on the Castle Rock. ($150)");
            port1Act2.setText("Old town - Visitors pack the Old Town, a bustling area of centuries-old buildings and narrow alleys. ($ 175)");
            port2Act1.setText("Gunnies House - Appreciate the beauty of Dublin, thorugh the magical Gunnies House($55)");
            port2Act2.setText("Dublin Castle is a major Irish government complex, conference centre, and tourist attraction. ($100)");


            port3.setText("");
            port3Act1.setText("");
            port3Act2.setText("");

        }
        if(background.equals("location4")){ // barcelona
            bgImage.setImageResource(R.drawable.disney4);
            port1.setText("Naples");
            port2.setText("Kannes");
            port1Act1.setText("TThe Royal Palace of Naples is a palace, museum, and historical tourist destination located in central Naples, southern Italy. ($30)");
            port1Act2.setText("Molo Beverello - The ports of beautiful restaurants and museums ($75)");
            port2Act1.setText("The Lérins Islands are a group of four Mediterranean islands off the French Riviera, in Cannes.($125)");
            port2Act2.setText("The Île Sainte-Marguerite is the largest of the Lérins Islands, about half a mile off shore from the French Riviera town of Cannes. ($ 70)");


            port3.setText("");
            port3Act1.setText("");
            port3Act2.setText("");

        }



    }
}
