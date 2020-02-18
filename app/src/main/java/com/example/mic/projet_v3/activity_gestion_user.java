package com.example.mic.projet_v3;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mic.projet_v3.DataAdapter.dataAdapter_user;
import com.example.mic.projet_v3.Tables.User;

import java.util.ArrayList;

public class activity_gestion_user extends AppCompatActivity {

    private EditText fname;
    private EditText fusername;
    private EditText fpassword;
    private ImageView pic;
    private DatabaseHandler db;
    private String f_name;
    private String f_username;
    private String f_password;
    private ListView lv;
    private dataAdapter_user data;
    private User dataModel;
    private Bitmap bp;
    private byte[] photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_user);

        //Instantiate database handler
        db=new DatabaseHandler(this);

        lv = (ListView) findViewById(R.id.list1);
        fname=(EditText) findViewById(R.id.txtname);
        fusername=(EditText) findViewById(R.id.txtusername);
        fpassword=(EditText) findViewById(R.id.txtpassword);





    }

    public void buttonClicked(View v){
        int id=v.getId();

        switch(id){

            case R.id.save:

                if(fname.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(),"Name edit text is empty, Enter name", Toast.LENGTH_LONG).show();
                }  else{
                    addUser();
                }

                break;

            case R.id.display:

                ShowRecords();
                break;
        }
    }

    // function to get values from the Edittext and image
    private void getValues(){
        f_name = fname.getText().toString();
        f_username = fusername.getText().toString();
        f_password = fpassword.getText().toString();
    }

    //Insert data to the database
    private void addUser(){
        getValues();

        db.addUser(new User(f_name,"","",f_username,f_password));
        Toast.makeText(getApplicationContext(),"Saved successfully", Toast.LENGTH_LONG).show();
    }

    //Retrieve data from the database and set to the list view
    private void ShowRecords(){
        final ArrayList<User> Users = new ArrayList<User>(db.getAllUsers());
        data=new dataAdapter_user(this, Users);

        lv.setAdapter(data);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                dataModel = Users.get(position);

                Toast.makeText(getApplicationContext(),String.valueOf(dataModel.getUser_id()), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
