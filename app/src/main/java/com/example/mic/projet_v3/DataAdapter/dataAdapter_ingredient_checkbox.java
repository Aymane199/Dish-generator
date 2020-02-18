package com.example.mic.projet_v3.DataAdapter;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mic.projet_v3.R;
import com.example.mic.projet_v3.Tables.Ingredient_checkbox;

import java.util.ArrayList;


public class dataAdapter_ingredient_checkbox extends ArrayAdapter<Ingredient_checkbox>{

    Context context;
    ArrayList<Ingredient_checkbox> list;
    boolean checkAll_flag = false;
    boolean checkItem_flag = false;

    public dataAdapter_ingredient_checkbox(Context context, ArrayList<Ingredient_checkbox> ingredient_checkbox){
        super(context, R.layout.list_checkbox_ingredients, ingredient_checkbox);
        this.context=context;
        this.list=ingredient_checkbox;
    }

    public  class  Holder{

        CheckBox checkBoxFV;
        TextView nameFV;
        ImageView pic;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position

        Ingredient_checkbox data = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view

        Holder viewHolder = null; // view lookup cache stored in tag

        if (convertView == null) {


            viewHolder = new Holder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_checkbox_ingredients, parent, false);

            viewHolder.nameFV = (TextView) convertView.findViewById(R.id.txtViewer);
            viewHolder.pic = (ImageView) convertView.findViewById(R.id.imgView);
            viewHolder.checkBoxFV = (CheckBox) convertView.findViewById(R.id.mycheckbox);

            viewHolder.checkBoxFV.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int  getPosition = (Integer) buttonView.getTag();
                    list.get(getPosition).setSelected(buttonView.isChecked());
                }
            });
            convertView.setTag(viewHolder);
            convertView.setTag(R.id.txtViewer, viewHolder.nameFV);
            convertView.setTag(R.id.mycheckbox, viewHolder.checkBoxFV);

        } else {
            viewHolder = (Holder) convertView.getTag();
        }

        viewHolder.checkBoxFV.setTag(position);

        viewHolder.nameFV.setText(" "+data.getIngredient_NAME()+"");
        viewHolder.pic.setImageBitmap(convertToBitmap(data.getIngredient_POTO()));
        viewHolder.checkBoxFV.setChecked(list.get(position).isSelected());


        // Return the completed view to render on screen
        return convertView;
    }
    //get bitmap image from byte array

    private Bitmap convertToBitmap(byte[] b){

        return BitmapFactory.decodeByteArray(b, 0, b.length);

    }

}