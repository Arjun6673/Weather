package com.arjunmore.weather;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class MainActivity extends AppCompatActivity {
  public   TextView txtCity,txtcontry,txtsunrise,txtsunset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCity =findViewById(R.id.txtcity);
        txtcontry = findViewById(R.id.txtContry);
        txtsunrise = findViewById(R.id.txtsunrise);
        txtsunset =findViewById(R.id.txtsunset);

        MyAsychtask myAsychtask = new MyAsychtask(MainActivity.this);
        myAsychtask.execute();
    }

    public void btngettt(View view) {

    }

    public  class MyAsychtask extends AsyncTask<String,Void,Weather>{

        Context context;
        ProgressDialog progressDialog;


       MyAsychtask(Context context){
            this.context = context;
            progressDialog = new ProgressDialog(context);
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setTitle("done");
            progressDialog.setMessage("Downloading..");
            progressDialog.show();

        }

        @Override
        protected Weather doInBackground(String... strings) {
             String data = Connect.GEtData();
             Weather weather = new Weather();
             weather = GetJson.GetJsonData(data);
             return weather;
        }


        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);
            txtCity.setText(weather.getCity());
            txtcontry.setText(weather.getCountry());
            txtsunrise.setText(weather.getSunrise());
            txtsunset.setText(weather.getSunset());
            if (progressDialog.isShowing()){
            progressDialog.dismiss();}
        }
    }
}
