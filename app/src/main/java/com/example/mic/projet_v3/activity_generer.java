package com.example.mic.projet_v3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.mic.projet_v3.DataAdapter.dataAdapter_ingredient_checkbox;
import com.example.mic.projet_v3.Tables.Ingredient_checkbox;

import java.util.ArrayList;

public class activity_generer extends AppCompatActivity {

    private DatabaseHandler db;
    private ListView lv;
    private dataAdapter_ingredient_checkbox data;
    private Ingredient_checkbox dataModel;
    private Button mybutton;
    private ArrayList<Ingredient_checkbox> ingredients;
    public String resultat="" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generer);

        //Instantiate database handler
        db=new DatabaseHandler(this);

        lv = (ListView) findViewById(R.id.list1);
        ShowRecords();
        mybutton = findViewById(R.id.showSelected);
        mybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            try{
                String res="";
                for (int i=0;i<ingredients.size();i++)
                {
                    if (ingredients.get(i).isSelected()) res+=ingredients.get(i).getIngredient_ID()+"-";
                }
                //Toast.makeText(getApplicationContext(),res, Toast.LENGTH_SHORT).show();
                resultat = res.substring(0, res.length() - 1);

                Intent myIntent = new Intent(activity_generer.this, activity_resultat.class);
                myIntent.putExtra("resultat", resultat);
                startActivity(myIntent);
            }catch (Exception e){
                e.printStackTrace();
            }
            }
        });

    }


    //Retrieve data from the database and set to the list view
    private void ShowRecords(){
      ingredients = new ArrayList<Ingredient_checkbox>(db.getAllingredients_checkbox());
        data = new dataAdapter_ingredient_checkbox(this, ingredients);

        lv.setAdapter(data);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                dataModel = ingredients.get(position);

                //Toast.makeText(getApplicationContext(),String.valueOf(dataModel.getIngredient_ID()), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
