package com.example.mic.projet_v3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button bntConnect;
    Button bntSignIn;
    EditText edUsername;
    EditText edPassword;
    DatabaseHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bntConnect = (Button) findViewById(R.id.btnConnect);
        bntSignIn = (Button) findViewById(R.id.btnSignIn);
        edUsername = (EditText) findViewById(R.id.edUsername);
        edPassword = (EditText) findViewById(R.id.edPassword);
        db = new DatabaseHandler(this);
//        int id = db.CheckUser(edUsername.getText().toString(),edPassword.getText().toString());

        bntConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = db.CheckUser(edUsername.getText().toString(),edPassword.getText().toString());
                //Toast.makeText(getApplicationContext(),id+"",Toast.LENGTH_SHORT).show();

                if(id != -1) {
                    Intent myIntent = new Intent(MainActivity.this, activity_menu.class);
                    myIntent.putExtra("id_user", id + "");
                    startActivity(myIntent);
                }
               }
        });
        bntSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this,activity_signin.class);
                startActivity(myIntent);
            }
        });
        

    }

}