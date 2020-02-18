package com.example.mic.projet_v3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mic.projet_v3.DataAdapter.dataAdapter_ingredient;
import com.example.mic.projet_v3.Tables.Ingredient;

import java.util.ArrayList;

public class activity_show_ingredient extends AppCompatActivity {

    private DatabaseHandler db;
    private ListView lv;
    private dataAdapter_ingredient data;
    private Ingredient dataModel;
    String ingredients;
    ArrayList<Integer> tab_ID_ingredint;
    ArrayList<Ingredient> tab_ingredint;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_ingredient);
        ingredients = getIntent().getStringExtra("id_ingredints");

        //Instantiate database handler
        db=new DatabaseHandler(this);

        lv = (ListView) findViewById(R.id.list1);
        get_Tab_ingredient();
        convert_Tab_ingre_from_id_to_ingredint();
        ShowRecords();



    }
    private void ShowRecords(){
        final ArrayList<Ingredient> ingredients = new ArrayList<>(tab_ingredint);
        data=new dataAdapter_ingredient(this, ingredients);

        lv.setAdapter(data);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                dataModel = ingredients.get(position);

                Toast.makeText(getApplicationContext(),String.valueOf(dataModel.getIngredient_ID()), Toast.LENGTH_SHORT).show();
            }
        });
    }
    void get_Tab_ingredient() {
        String[] parts = ingredients.split("-");
        tab_ID_ingredint = new ArrayList<>();

        for (int i = 0 ; i< parts.length;i++)
        {
            tab_ID_ingredint.add(Integer.parseInt(parts[i]));
        }
    }

    void convert_Tab_ingre_from_id_to_ingredint()
    {
        tab_ingredint = new ArrayList<>();
        for (int i=0;i<tab_ID_ingredint.size();i++)
        {
            tab_ingredint.add(db.get_ingredients_by_id(tab_ID_ingredint.get(i)));
        }
    }
}