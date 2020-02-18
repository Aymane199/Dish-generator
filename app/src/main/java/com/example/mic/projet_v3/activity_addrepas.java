package com.example.mic.projet_v3;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mic.projet_v3.DataAdapter.dataAdapter_ingredient_checkbox;
import com.example.mic.projet_v3.Tables.Ingredient_checkbox;
import com.example.mic.projet_v3.Tables.Repas;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class activity_addrepas extends AppCompatActivity {

    private EditText fname;
    private ImageView pic;
    private DatabaseHandler db;
    private String f_name;
    private ListView lv;
    private dataAdapter_ingredient_checkbox data;
    private Ingredient_checkbox dataModel;
    private Bitmap bp;
    private byte[] photo;
    private ArrayList<Ingredient_checkbox> ingredients;
    public String resultat="" ;
    private ImageView btnpic;
    private ImageView btnsave;
    private int id_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addrepas);

        id_user = Integer.parseInt(getIntent().getStringExtra("id_user"));
        //Instantiate database handler
        db=new DatabaseHandler(this);

        lv = (ListView) findViewById(R.id.list1);
        pic= (ImageView) findViewById(R.id.pic);
        btnsave= (ImageView) findViewById(R.id.save);
        fname=(EditText) findViewById(R.id.txt1);

        btnpic =(ImageView) findViewById(R.id.pic);
        btnpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fname.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(),"Name edit text is empty, Enter name", Toast.LENGTH_LONG).show();
                }  else{
                    try{
                        addrepas();
                    }catch (Exception e){
                        e.printStackTrace();
                    }}
            }
        });
        ShowRecords();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.done , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.item1 :
                if(fname.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(),"Name edit text is empty, Enter name", Toast.LENGTH_LONG).show();
                }  else{
                    try{
                        addrepas();
                    }catch (Exception e){
                        e.printStackTrace();
                    }}
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void selectImage(){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, 2);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case 2:
                if(resultCode == RESULT_OK){
                    Uri choosenImage = data.getData();

                    if(choosenImage !=null){

                        bp=decodeUri(choosenImage, 400);
                        pic.setImageBitmap(bp);
                    }
                }
        }
    }

    //COnvert and resize our image to 400dp for faster uploading our images to DB
    protected Bitmap decodeUri(Uri selectedImage, int REQUIRED_SIZE) {

        try {

            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o);

            // The new size we want to scale to
            // final int REQUIRED_SIZE =  size;

            // Find the correct scale value. It should be the power of 2.
            int width_tmp = o.outWidth, height_tmp = o.outHeight;
            int scale = 1;
            while (true) {
                if (width_tmp / 2 < REQUIRED_SIZE
                        || height_tmp / 2 < REQUIRED_SIZE) {
                    break;
                }
                width_tmp /= 2;
                height_tmp /= 2;
                scale *= 2;
            }

            // Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o2);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //Convert bitmap to bytes
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    private byte[] profileImage(Bitmap b){

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 0, bos);
        return bos.toByteArray();

    }

    // function to get values from the Edittext and image
    private void getValues(){
        f_name = fname.getText().toString();
        photo = profileImage(bp);
    }

    //Insert data to the database
    private void addrepas(){
        getValues();
        String res="";
        for (int i=0;i<ingredients.size();i++)
        {
            if (ingredients.get(i).isSelected()) res+=ingredients.get(i).getIngredient_ID()+"-";
        }
        //Toast.makeText(getApplicationContext(),res, Toast.LENGTH_SHORT).show();
        resultat = res.substring(0, res.length() - 1);


        db.addrepas(new Repas(f_name,resultat, photo,id_user));
        Toast.makeText(getApplicationContext(),"Saved successfully "+resultat   , Toast.LENGTH_LONG).show();

        Intent myIntent = new Intent(activity_addrepas.this, activity_addetape.class);
       myIntent.putExtra("id_repas", db.getMaxidRepas()+ "" );
       myIntent.putExtra("id_user", id_user + "");
       startActivity(myIntent);
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

               // Toast.makeText(getApplicationContext(),String.valueOf(dataModel.getIngredient_ID()), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
