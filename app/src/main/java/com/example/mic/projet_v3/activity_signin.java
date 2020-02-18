package com.example.mic.projet_v3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mic.projet_v3.Tables.User;

public class activity_signin extends AppCompatActivity {

    Button bntSignUp;
    Button bntCancel;
    EditText edName;
    EditText edAge;
    EditText edSexe;
    EditText edUsername;
    EditText edPassword;
    EditText edPassword2;

    String name;
    String age;
    String sex;
    String username;
    String password;
    String password2;

    DatabaseHandler db ;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        db = new DatabaseHandler(this);
        bntSignUp = (Button) findViewById(R.id.btnSignUp);
        bntCancel = (Button) findViewById(R.id.btnCancel);
        edName = (EditText) findViewById(R.id.edName);
        edAge = (EditText) findViewById(R.id.edAge) ;
        edSexe = (EditText) findViewById(R.id.edSexe) ;
        edUsername = (EditText) findViewById(R.id.edUsername) ;
        edPassword = (EditText) findViewById(R.id.edPassword) ;
        edPassword2 = (EditText) findViewById(R.id.edPassword2) ;


        bntSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getvalue();
                if(name.trim().equals("")
                        && age.trim().equals("")
                && sex.trim().equals("")
                && username.trim().equals("")
                && password.trim().equals("")
                && password2.trim().equals("")){
                    Toast.makeText(getApplicationContext(),"edit text is empty", Toast.LENGTH_LONG).show();
                }else if(!password.trim().equals(password2)){
                    Toast.makeText(getApplicationContext(),"password not match", Toast.LENGTH_LONG).show();
                }else{
                    addUser();
                    Intent myIntent = new Intent(activity_signin.this,activity_menu.class);
                    myIntent.putExtra("id_user", db.CheckUser(username,password) + "");
                    startActivity(myIntent);
                }


            }
        });
        bntCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(activity_signin.this,MainActivity.class);
                startActivity(myIntent);
            }
        });
    }
    void getvalue()
    {
         name=edName.getText().toString();
         age=edAge.getText().toString();
         sex=edSexe.getText().toString();
         username=edUsername.getText().toString();
         password=edPassword.getText().toString();
         password2=edPassword2.getText().toString();
    }
    private void addUser(){

        db.addUser(new User(name,age,sex,username,password));
        Toast.makeText(getApplicationContext(),"Saved successfully", Toast.LENGTH_LONG).show();
    }
}
