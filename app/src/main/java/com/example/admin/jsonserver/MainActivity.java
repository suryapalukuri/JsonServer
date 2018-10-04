package com.example.admin.jsonserver;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    private RecyclerView recyclerView;
    private List<JobListItem> jobListItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        jobListItems=new ArrayList<>();
        String url="https://api.androidhive.info/contacts/";
        new ResponseAsync().execute(url);
    }

    private class ResponseAsync extends AsyncTask<String,Void,String > {
        protected void onPreExecute(){
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Response from url");
            progressDialog.setMessage("Loading...");
            progressDialog.setIndeterminate(false);
            progressDialog.show();
        }
        @Override
        protected String doInBackground(String... urls) {

            String response=urls[0];
            Log.i("call",response);
            try{
                URL url=new URL(response);
                InputStream is=url.openConnection().getInputStream();
                StringBuffer buffer=new StringBuffer();
                BufferedReader reader=new BufferedReader(new InputStreamReader(is));
                String line;
                while ((line=reader.readLine()) != null){
                    buffer.append(line+"\n");
                    Log.i("Why data ", buffer.toString());

                }
                return buffer.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
        protected void onPostExecute(String result) {
            Log.i("Response data", result);
            try {
                JSONObject object = new JSONObject(result);
                JSONArray array = object.getJSONArray("contacts");

                for (int i =0; i<array.length(); i++){
                    JobListItem item = new JobListItem();
                    JSONObject list_obj = array.getJSONObject(i);
                    item.setId(list_obj.getString("id"));
                    item.setName(list_obj.getString("name"));
                    item.setEmail(list_obj.getString("email"));
                    item.setAddress(list_obj.getString("address"));
                    item.setGender(list_obj.getString("gender"));

                    JSONObject ph = list_obj.getJSONObject("phone");
//

                    item.setMobile(ph.getString("mobile"));
                    item.setHome(ph.getString("home"));
                    item.setOffice(ph.getString("office"));

                    jobListItems.add(item);

                }

                recyclerView.setAdapter(new JobListAdapter(MainActivity.this, jobListItems));

            } catch (JSONException e) {

                e.printStackTrace();

            }

            progressDialog.dismiss();

        }

    }
    }

