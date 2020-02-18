package com.example.mic.projet_v3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mic.projet_v3.Tables.Repas;
import com.example.mic.projet_v3.Tables.User;

public class activity_details_repas extends AppCompatActivity {

    private DatabaseHandler db;

    ImageView img;
    TextView titre;
    TextView createur;
    Button ingredients ;
    Button etape_res;
    Repas data;
    ImageView profile;

    int id;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_repas);
        id = getIntent().getIntExtra("id_repas",0);

        db=new DatabaseHandler(this);

        data = db.get_repass_by_id(id);
        user = db.getUser_by_id(data.getREPAS_ID_USER());
        img = findViewById(R.id.pic);
        titre = findViewById(R.id.titre);
        createur = findViewById(R.id.createur);
        img.setImageBitmap(convertToBitmap(data.getREPAS_POTO()));
        profile = (ImageView) findViewById(R.id.profile);

        ingredients =(Button) findViewById(R.id.btningredient);
        etape_res = (Button)findViewById(R.id.btnetapes);

        titre.setText(data.getREPAS_NAME());
        createur.setText(createur.getText()+" : "+user.getUser_name()+"");

        ingredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(activity_details_repas.this,activity_show_ingredient.class);
                myIntent.putExtra("id_ingredints",data.getREPAS_TAB_Ingredient());
                startActivity(myIntent);
            }
        });
        etape_res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(activity_details_repas.this,activity_show_etapes.class);
                myIntent.putExtra ("id_repas",id+"");
                startActivity(myIntent);
                }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(activity_details_repas.this, activity_profile.class);
                myIntent.putExtra("id_user", user.getUser_id() + "");
                myIntent.putExtra("from", 0 + "");
                startActivity(myIntent);
            }
          });
        createur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(activity_details_repas.this, activity_profile.class);
                myIntent.putExtra("id_user", user.getUser_id() + "");
                myIntent.putExtra("from", 0 + "");
                startActivity(myIntent);
            }
          });

    }




    private Bitmap convertToBitmap(byte[] b){

        return BitmapFactory.decodeByteArray(b, 0, b.length);

    }

}
