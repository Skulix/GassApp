package com.example.henri.gassapp;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class GetInformations extends AppCompatActivity {


    boolean alcool = false;
    boolean gasolina = false;
    boolean gnv = false;
    int distancia = 0;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_informations);

        final CheckBox gasolinaBox = (CheckBox)findViewById(R.id.gasolinaBox);
        final CheckBox gasBox = (CheckBox)findViewById(R.id.gasBox);
        final CheckBox alcoolBox = (CheckBox)findViewById(R.id.alcoolBox);

        final EditText raio = (EditText)findViewById(R.id.editText);

        Button attBtn = (Button)findViewById(R.id.attBtn);
        Button searchBtn = (Button)findViewById(R.id.searchBtn);


        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gasBox.isChecked()){
                    gnv = true;
                }
                if (gasolinaBox.isChecked()){
                    gasolina = true;
                }
                if (alcoolBox.isChecked()) {
                    alcool = true;
                }
                distancia = !raio.getText().toString().equals("") ? Integer.parseInt(raio.getText().toString()) : 0;
                goToShowGasStations();
            }
        });

        attBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog = new ProgressDialog(GetInformations.this);

                MyAsyncTask getOnline = new MyAsyncTask(progressDialog, 170, GetInformations.this);
                getOnline.execute();

            }
        });

    }

    public void goToShowGasStations(){
        Intent intent = new Intent(this, ShowGasStationsList.class);
        intent.putExtra("alcool", alcool);
        intent.putExtra("gasolina", gasolina);
        intent.putExtra("gnv", gnv);
        intent.putExtra("distancia", distancia);
        startActivity(intent);
    }

}

class MyAsyncTask extends AsyncTask<String, String, Void> {

    InputStream inputStream = null;
    String result = "";
    ProgressDialog barraProgresso;
    int codigoCidade = 0;

    Context context;

    public MyAsyncTask(ProgressDialog _prog, int _codigoCidade, Context context){

        this.barraProgresso = _prog;
        this.codigoCidade = _codigoCidade;
        this.context = context;
    }

    @Override
    protected Void doInBackground(String... params) {

        String url_select = "http://api.meuspostos.com.br/busca.json?chave=&cidade="+codigoCidade;

        controllerPosto controllerPosto = new controllerPosto(context);
        modelPosto posto;

        try {

            URL url = new URL(url_select);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod("GET");
            InputStream in = urlConnection.getInputStream();
            String resultado = convertStreamToString(in);

            JSONObject json = new JSONObject(resultado);

            if(json != null){
                JSONObject data = json.getJSONObject("data");
                if (data != null){
                    JSONArray postos = data.getJSONArray("busca");
                    if (postos != null){
                        controllerPosto.dropDatabase();
                        for (int i = 0; i < postos.length(); i++) {
                            posto = new modelPosto();
                            JSONObject jsonobject = postos.getJSONObject(i);
                            posto.setNome(jsonobject.getString("nome"));
                            posto.setEndereco(jsonobject.getString("endereco"));
                            posto.setBairro(jsonobject.getString("bairro"));
                            posto.setDt_pesquisa(jsonobject.getString("dt_pesquisa"));
                            posto.setBandeira(jsonobject.getString("bandeira"));
                            posto.setGasolina(jsonobject.getString("gasolina")!= null ? Float.parseFloat(jsonobject.getString("gasolina")):  0);
                            posto.setAlcool(jsonobject.getString("alcool")!= null ? Float.parseFloat(jsonobject.getString("alcool")):  0);
                            posto.setDiesel(jsonobject.getString("diesel")!= null ? Float.parseFloat(jsonobject.getString("diesel")):  0);
                            posto.setGnv(jsonobject.getString("gnv")!= null ? Float.parseFloat(jsonobject.getString("gnv")):  0);
                            controllerPosto.inserePostoObjeto(posto);
                        }
                    }
                }
            }

        }catch (JSONException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    protected void onPreExecute() {
        if(barraProgresso!= null) {
            barraProgresso.setMessage("Downloading your data...");
            barraProgresso.show();
            barraProgresso.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface arg0) {
                    MyAsyncTask.this.cancel(true);
                }
            });
        }
    }

    protected void onPostExecute(Void v) {

        if(barraProgresso != null){
            barraProgresso.dismiss();
        }

    } // protected void onPostExecute(Void v)

    private String convertStreamToString(InputStream _in){

        BufferedReader reader = new BufferedReader(new InputStreamReader(_in));
        StringBuilder stringBuilder = new StringBuilder();

        String line;
        try{
            while ((line = reader.readLine()) != null){
                stringBuilder.append(line);
            }
        }catch (IOException ioerro){
            ioerro.printStackTrace();
        }finally {
            try{
                _in.close();
            }catch (IOException ior){
                ior.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }
}
