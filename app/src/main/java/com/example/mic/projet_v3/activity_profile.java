package com.example.mic.projet_v3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mic.projet_v3.Tables.User;

public class activity_profile extends AppCompatActivity {

    Button bntCancel;
    TextView edName;
    TextView edAge;
    TextView edSexe;
    TextView edUsername;
    TextView ednbrepas;

    DatabaseHandler db ;

    User user;

    int id_user;
    int from;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        id_user = Integer.parseInt(getIntent().getStringExtra("id_user"));
        from = Integer.parseInt(getIntent().getStringExtra("from"));

        bntCancel = (Button) findViewById(R.id.btnCancel);
        edName = (TextView) findViewById(R.id.edName);
        edAge = (TextView) findViewById(R.id.edAge) ;
        edSexe = (TextView) findViewById(R.id.edSexe) ;
        edUsername = (TextView) findViewById(R.id.edUsername) ;
        ednbrepas = (TextView) findViewById(R.id.ednbrepas) ;

        db = new DatabaseHandler(this);

        user = db.getUser_by_id(id_user);

        edName.setText(user.getUser_name());
        edAge.setText(user.getUser_age());
        edSexe.setText(user.getUser_sexe());  ;
        edUsername.setText(user.getUser_username());  ;
        ednbrepas.setText(db.get_nb_Repas_by_id_user(id_user)+"");

        bntCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity_profile.super.onBackPressed();
            }
        });



    }
}
