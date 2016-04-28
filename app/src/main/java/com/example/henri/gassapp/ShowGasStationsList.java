package com.example.henri.gassapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShowGasStationsList extends AppCompatActivity {

    String myJson;
    private static final String TAG_RESULTS = "result";
    private static final String TAG_NOME = "name";
    private static final String TAG_GASOLINA = "gas";
    private static final String TAG_ID = "ID";

    JSONArray postos = null;

    ArrayList<HashMap<String, String>> postosList;

    ListView list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_gas_stations_list);

        list = (ListView) findViewById(R.id.listview);

        postosList = new ArrayList<HashMap<String, String>>();

      //  getData();

        /*

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }
        final StableArrayAdapter adapter = new StableArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                view.animate().setDuration(2000).alpha(0)
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                adapter.remove(item);
                                adapter.notifyDataSetChanged();
                                view.setAlpha(1);
                            }
                        });
            }

        }); */
    }

    /*
    public void getData(){
        class GetDataJSON extends AsyncTask<String, Void, String>{
            protected String doInBackground(String...params){
                DefaultHttpClient httpclient = new DefaultHttpCliente(new BasicHttpParams());
                HttpPost httppost = new HttpPost("http://");
                httppost.setHeader("Content-type", "application/json");

                InputStream inputStream = null;
                String result = null;
                try{
                    HttpResponse response = httpclient.execute(httppost);

                }
            }

        }

    }
    */


    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }

}
