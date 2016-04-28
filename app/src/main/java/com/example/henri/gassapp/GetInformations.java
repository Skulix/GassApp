package com.example.henri.gassapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class GetInformations extends AppCompatActivity {
    final boolean alcool = false;
    final boolean gasolina = false;
    final int distancia = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_informations);

        final boolean alcool = false;
        final boolean gasolina = false;
        final int distancia = 0;



        Button searchBtn = (Button)findViewById(R.id.searchBtn);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToShowGasStations();
            }
        });

    }

    public void goToShowGasStations(){
        Intent intent = new Intent(this, ShowGasStationsList.class);
        intent.putExtra("alcool", alcool);
        intent.putExtra("gasolina", gasolina);
        intent.putExtra("distancia", distancia);
        startActivity(intent);
    }


}
