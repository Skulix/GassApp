package com.example.henri.gassapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by henri on 29/04/2016.
 */
public class StableArrayAdapter extends BaseAdapter {

    private Context context = null;
    private ArrayList<modelPosto> listaPostos = null;

    public StableArrayAdapter(Context context, ArrayList<modelPosto> listaPostos){
        try {
            this.context = context;
            this.listaPostos = listaPostos;
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public int getCount() {
        return listaPostos.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.posto_detail,parent,false);

        TextView postoName = (TextView)rowView.findViewById(R.id.postoName);
        TextView alcoolPrice = (TextView)rowView.findViewById(R.id.alcoolPrice);
        TextView gasolinaPrice = (TextView)rowView.findViewById(R.id.gasolinaPrice);
        TextView dieselPrice = (TextView)rowView.findViewById(R.id.dieselPrice);

        postoName.setText(listaPostos.get(position).getNome());
        gasolinaPrice.setText(String.valueOf(listaPostos.get(position).getGasolina()));
        alcoolPrice.setText(String.valueOf(listaPostos.get(position).getAlcool()));
        dieselPrice.setText(String.valueOf(listaPostos.get(position).getDiesel()));
        return rowView;
    }
}