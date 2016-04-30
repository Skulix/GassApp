package com.example.henri.gassapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by henri on 29/04/2016.
 */
public class StableArrayAdapter extends BaseAdapter {

    Context context;
    ArrayList<modelPosto> listaPostos;

    private static LayoutInflater inflater = null;

    public StableArrayAdapter(Context context, ArrayList<modelPosto> listaPostos){
        this.context = context;
        this.listaPostos = listaPostos;
    }
    @Override
    public int getCount() {
        return 0;
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
        Holder holder = new Holder();
        View v = inflater.inflate(R.layout.posto_detail,null);

        holder.postoName=(TextView)v.findViewById(R.id.postoName);
        holder.alcoolPrice=(TextView)v.findViewById(R.id.alcoolPrice);
        holder.dieselPrice=(TextView)v.findViewById(R.id.dieselPrice);
        holder.gasolinaPrice=(TextView)v.findViewById(R.id.gasolinaPrice);

        holder.postoName.setText(listaPostos.get(position).getNome());
        holder.gasolinaPrice.setText(String.valueOf(listaPostos.get(position).getGasolina()));
        holder.alcoolPrice.setText(String.valueOf(listaPostos.get(position).getAlcool()));
        holder.dieselPrice.setText(String.valueOf(listaPostos.get(position).getDiesel()));
        return v;
    }

    public class Holder{
        TextView postoName;
        TextView alcoolPrice;
        TextView gasolinaPrice;
        TextView dieselPrice;
    }

}