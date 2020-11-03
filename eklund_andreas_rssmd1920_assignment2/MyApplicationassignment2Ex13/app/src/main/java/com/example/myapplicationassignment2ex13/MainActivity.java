package com.example.myapplicationassignment2ex13;

import android.Manifest;
import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import android.content.ClipboardManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;


import static java.lang.String.valueOf;

public class MainActivity extends AppCompatActivity implements LocationListener {
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected String lat,longi;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (locationManager != null) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                return;
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);


        }


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "your location " + lat + ", "+ longi, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("loc", lat+ " "+ longi);
                clipboard.setPrimaryClip(clip);
            }
        });


    }

    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location) {

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    @Override
    public void onLocationChanged(Location location) {
        Context context = this.getApplicationContext();

        lat = valueOf(location.getLatitude());
        longi = valueOf(location.getLongitude());
        Intent intentLocation = new Intent();
        intentLocation.setAction("com.example.assignment2_app2");
        intentLocation.setClassName("com.example.assignment2_app2","com.example.assignment2_app2.MainActivity");
        intentLocation.putExtra("loc", lat+","+longi);
        startService(intentLocation);
        context.sendBroadcast(intentLocation);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

//    public void createNote(String subject, String text) {
//        Intent intent = new Intent(NoteIntents.ACTION_CREATE_NOTE)
//                .putExtra(NoteIntents.EXTRA_NAME, subject)
//                .putExtra(NoteIntents.EXTRA_TEXT, text);
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }
//    }

}
