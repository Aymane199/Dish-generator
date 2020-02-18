package com.example.mic.projet_v3.DataAdapter;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mic.projet_v3.R;
import com.example.mic.projet_v3.Tables.Etape;

import java.util.ArrayList;


public class dataAdapter_etape extends ArrayAdapter<Etape>{

    Context context;
    ArrayList<Etape> metape;


    public dataAdapter_etape(Context context, ArrayList<Etape> etape){
        super(context, R.layout.listetapes, etape);
        this.context=context;
        this.metape=etape;
    }

    public  class  Holder{
        TextView nameFV;
        TextView descFV;
        ImageView pic;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position

        Etape data = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view

        Holder viewHolder; // view lookup cache stored in tag

        if (convertView == null) {


            viewHolder = new Holder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.listetapes, parent, false);

            viewHolder.nameFV = (TextView) convertView.findViewById(R.id.txtViewer);
            viewHolder.descFV = (TextView) convertView.findViewById(R.id.txtViewer1);
            viewHolder.pic = (ImageView) convertView.findViewById(R.id.imgView);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (Holder) convertView.getTag();
        }


        viewHolder.nameFV.setText(" Name: "+data.getEtape_NAME());
        viewHolder.descFV.setText("desc: "+data.getEtape_Description());
        viewHolder.pic.setImageBitmap(convertToBitmap(data.getEtape_POTO()));


        // Return the completed view to render on screen
        return convertView;
    }
    //get bitmap image from byte array

    private Bitmap convertToBitmap(byte[] b){

        return BitmapFactory.decodeByteArray(b, 0, b.length);

    }

}