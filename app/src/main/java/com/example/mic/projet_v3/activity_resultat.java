package com.example.mic.projet_v3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mic.projet_v3.DataAdapter.dataAdapter_repas;
import com.example.mic.projet_v3.Tables.Repas;

import java.util.ArrayList;
import java.util.List;

public class activity_resultat extends AppCompatActivity {

    ArrayList<Integer> tab_ID_ingredint;
    List<Repas> tab_repas;
    String ingredients;
    DatabaseHandler db;
    TextView resultat_ingredint;
    TextView resultat_repas;
    dataAdapter_repas data;
    ListView lv ;
    List<Repas> tab_resulat ;
    private Repas dataModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);
        db = new DatabaseHandler(this);
        lv = (ListView) findViewById(R.id.list1);
        ingredients = getIntent().getStringExtra("resultat");
        if(!ingredients.equals("")) {

            get_Tab_ingredient();
            tab_repas = db.getAllrepass();

            tab_resulat = get_resultat_repas();
            ShowRecords();
        }else{
            ShowRecords1();
        }

    }

    void get_Tab_ingredient() {
        String[] parts = ingredients.split("-");
        tab_ID_ingredint = new ArrayList<>();

        for (int i = 0 ; i< parts.length;i++)
        {
            tab_ID_ingredint.add(Integer.parseInt(parts[i]));
        }
     }

    boolean find_id_in_tab(int id , List<Integer> tab){

        for(int i = 0 ; i<tab.size();i++)
        {
            if(id == tab.get(i)) return true;
        }
        return false;
    }

    boolean sous_tab(List<Integer> subtab,List<Integer> tab)
    {
        for(int i = 0 ; i<subtab.size();i++)
        {
            if (!find_id_in_tab(subtab.get(i),tab))
            {
                return false;
            }
        }
        return true;
    }

    List<Repas> get_resultat_repas() {
        List<Repas> repasList = new ArrayList<Repas>();
        for (int i = 0; i < tab_repas.size(); i++) {

            String[] parts = tab_repas.get(i).getREPAS_TAB_Ingredient().split("-");
            List<Integer> tab_ID_ingredint_repas = new ArrayList<>();

            for (int j = 0; j < parts.length; j++) {
                tab_ID_ingredint_repas.add(Integer.parseInt(parts[j]));
            }


            if (sous_tab(tab_ID_ingredint, tab_ID_ingredint_repas)) {
                repasList.add(tab_repas.get(i));
            }
        }
        return repasList;
    }

    private void ShowRecords(){
        final ArrayList<Repas> repass = new ArrayList<>(tab_resulat);
        data=new dataAdapter_repas(this, repass);

        lv.setAdapter(data);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                dataModel = repass.get(position);

                //Toast.makeText(getApplicationContext(),String.valueOf(dataModel.getREPAS_ID()), Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(activity_resultat.this, activity_details_repas.class);
                myIntent.putExtra("id_repas", dataModel.getREPAS_ID());
                startActivity(myIntent);
            }
        });}

        private void ShowRecords1(){
        final ArrayList<Repas> repass2 = new ArrayList<Repas>(db.getAllrepass());
        data=new dataAdapter_repas(this, repass2);

        lv.setAdapter(data);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                dataModel = repass2.get(position);

                //Toast.makeText(getApplicationContext(),String.valueOf(dataModel.getREPAS_ID()), Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(activity_resultat.this, activity_details_repas.class);
                myIntent.putExtra("id_repas", dataModel.getREPAS_ID());
                startActivity(myIntent);
            }
        });}
}


