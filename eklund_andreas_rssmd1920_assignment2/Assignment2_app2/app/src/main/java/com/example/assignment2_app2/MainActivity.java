package com.example.assignment2_app2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    String Loc = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String DownloadSpeed = getbandwith();
                Snackbar.make(view, "Download speed = "+ DownloadSpeed+ " Megabytes per second"+ Loc, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }
    public class reciever extends BroadcastReceiver{
        @Override
        public void onReceive (Context context , Intent intent ) {
            Loc = String.valueOf(intent.getStringExtra("loc"));//here is where the app is supposed to recieve the location
            return;
        }   }
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

    public String getbandwith(){
        Context context = this;
        //function that gets the device's bandwith
        ConnectivityManager cm;
        cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkCapabilities nc = cm.getNetworkCapabilities(cm.getActiveNetwork());
        String DownloadSpeed = String.valueOf(nc.getLinkDownstreamBandwidthKbps()/8000);
        return DownloadSpeed;
    }

}
