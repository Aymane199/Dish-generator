package com.example.mic.projet_v3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class activity_menu extends AppCompatActivity {

    Button bntingredient;
    Button bntgenerer;
    Button bntaddRepas;
    Button bntrepas;
    int id;
    ImageView profile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        id = Integer.parseInt(getIntent().getStringExtra("id_user"));
        bntingredient = (Button) findViewById(R.id.btningredient);
        bntgenerer = (Button) findViewById(R.id.btngenerer);
        bntaddRepas = (Button) findViewById(R.id.btnAddRepas);
        bntrepas = (Button) findViewById(R.id.btnrepas);
        profile = (ImageView) findViewById(R.id.profile);

        bntingredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(activity_menu.this,activity_gestion_ingredient.class);
                myIntent.putExtra("id_user", id + "");
                startActivity(myIntent);
            }
        });
        bntgenerer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(activity_menu.this,activity_generer.class);
                startActivity(myIntent);
            }
        });
        bntaddRepas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(activity_menu.this,activity_addrepas.class);
                myIntent.putExtra("id_user", id + "");
                startActivity(myIntent);
            }
        });
        bntrepas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(activity_menu.this,activity_resultat.class);
                myIntent.putExtra("resultat", "");
                startActivity(myIntent);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(activity_menu.this,activity_profile.class);
                myIntent.putExtra("id_user", id + "");
                myIntent.putExtra("from",  0+"");
                startActivity(myIntent);
            }
        });


    }


}
