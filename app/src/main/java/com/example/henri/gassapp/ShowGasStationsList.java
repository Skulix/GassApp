package com.example.henri.gassapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShowGasStationsList extends AppCompatActivity {

    final ArrayList<modelPosto> list = new ArrayList<>();

    boolean gnv;
    boolean gasolina;
    boolean alcool;
    int distancia;



    ArrayList<modelPosto> listaPostos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_gas_stations_list);
        Intent intent = getIntent();

        ListView listView = (ListView) findViewById(R.id.listViewPostos);

        gnv = intent.getBooleanExtra("gnv", gnv);
        gasolina = intent.getBooleanExtra("gasolina", gasolina);
        alcool = intent.getBooleanExtra("alcool", alcool);
        distancia = intent.getIntExtra("distancia", distancia);


        listaPostos = new ArrayList<>();
        listaPostos = new controllerPosto(this).preencheSpinner();

        try{
            listView.setAdapter(new StableArrayAdapter(this, listaPostos));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
