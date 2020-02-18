package com.example.mic.projet_v3.DataAdapter;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mic.projet_v3.R;
import com.example.mic.projet_v3.Tables.User;

import java.util.ArrayList;


public class dataAdapter_user extends ArrayAdapter<User>{

    Context context;
    ArrayList<User> muser;


      public dataAdapter_user(Context context, ArrayList<User> user){
        super(context, R.layout.listusers, user);
        this.context=context;
        this.muser=user;
    }

    public  class  Holder{
        TextView nameFV;
        TextView usernameFV;
        TextView passwordFV;
        TextView idFV;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        User data = getItem(position);
        Holder viewHolder;

        if (convertView == null) {

            viewHolder = new Holder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.listusers, parent, false);

            viewHolder.nameFV = (TextView) convertView.findViewById(R.id.txtname);
            viewHolder.usernameFV = (TextView) convertView.findViewById(R.id.txtusername);
            viewHolder.passwordFV = (TextView) convertView.findViewById(R.id.txtpassword);
            viewHolder.idFV = (TextView) convertView.findViewById(R.id.txtid);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (Holder) convertView.getTag();
        }

        viewHolder.nameFV.setText("Name: "+data.getUser_name());
        viewHolder.usernameFV.setText("userName: "+data.getUser_username());
        viewHolder.passwordFV.setText("password: "+data.getUser_password());
        viewHolder.idFV.setText("id: "+data.getUser_id());


        // Return the completed view to render on screen
        return convertView;
    }
    //get bitmap image from byte array

    private Bitmap convertToBitmap(byte[] b){

        return BitmapFactory.decodeByteArray(b, 0, b.length);

    }

}