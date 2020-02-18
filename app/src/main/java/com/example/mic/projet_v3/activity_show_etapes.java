package com.example.mic.projet_v3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.mic.projet_v3.DataAdapter.dataAdapter_etape;
import com.example.mic.projet_v3.Tables.Etape;

import java.util.ArrayList;

public class activity_show_etapes extends AppCompatActivity {

    private DatabaseHandler db;
    private ListView lv;
    private dataAdapter_etape data;
    private Etape dataModel;
    int repas;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_etapes);
        repas = Integer.parseInt(getIntent().getStringExtra("id_repas"));

        //Instantiate database handler
        db=new DatabaseHandler(this);

        lv = (ListView) findViewById(R.id.list1);

        ShowRecords();



    }
    private void ShowRecords(){
        final ArrayList<Etape> etapes = new ArrayList<>(db.getAlletape_by_idrepas(repas));

        data=new dataAdapter_etape(this, etapes);

        lv.setAdapter(data);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                dataModel = etapes.get(position);

                //Toast.makeText(getApplicationContext(),String.valueOf(dataModel.getEtape_NAME()), Toast.LENGTH_SHORT).show();
            }
        });
    }

}