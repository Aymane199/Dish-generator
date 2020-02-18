package com.example.mic.projet_v3;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mic.projet_v3.Tables.Etape;

import java.io.ByteArrayOutputStream;

public class activity_addetape extends AppCompatActivity {

    private EditText fname;
    private EditText fdesc;
    private TextView titre;
    private ImageView pic;
    private DatabaseHandler db;
    private String f_name;
    private String f_desc;
    private Bitmap bp;
    private byte[] photo;
    private  int id_repas;
    private  int id_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addetape);

        db=new DatabaseHandler(this);
        id_repas = Integer.parseInt(getIntent().getStringExtra("id_repas"));
        id_user = Integer.parseInt(getIntent().getStringExtra("id_user"));
        pic= (ImageView) findViewById(R.id.pic);
        fname=(EditText) findViewById(R.id.txt1);
        fdesc = findViewById(R.id.mydesc);
        titre = findViewById(R.id.titreetape);

        db=new DatabaseHandler(this);


    }

    public void buttonClicked(View v){
        int id=v.getId();

        switch(id){

            case R.id.nextStep:

                if(fname.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(),"Name edit text is empty, Enter name", Toast.LENGTH_LONG).show();
                }  else{
                    addetape();
                }

                break;

            case R.id.exit:
                Intent myIntent = new Intent(activity_addetape.this,activity_menu.class);
                myIntent.putExtra("id_user",id_user+"");
                startActivity(myIntent);
                break;
            case R.id.pic:
                selectImage();
                break;
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

    private void getValues(){
        f_name = fname.getText().toString();
        f_desc = fdesc.getText().toString();
        photo = profileImage(bp);
    }

    private void addetape(){

        try{
        getValues();
        db.addetape(new Etape(f_name,f_desc, photo,id_repas));


        Toast.makeText(getApplicationContext(),"Saved successfully", Toast.LENGTH_LONG).show();

            fname.setText("");
            fdesc.setText("");
            pic.setImageResource(R.drawable.choose_up);

        }catch (Exception e){
        e.printStackTrace();
    }
    }


}
