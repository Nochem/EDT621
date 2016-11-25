package com.exist.doesnt.g.myfirstapp;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.util.Log;
import android.content.Intent;
import android.app.Activity;


public class MainActivity extends AppCompatActivity {

    String msg = "MainActivity ";
    String savedNetwork;
    //EditText input = (EditText) findViewById(R.id.editText);

    /** Called when the activity is first created */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(msg, "The onCreate() event");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }

    /** Save the string to a reminder */
    public void save(View view){
        EditText input = (EditText) findViewById(R.id.editText);
        MyService.setReminder(input.getText().toString());
        Log.d(msg, "SAVE CAllED");
    }
    
    public void wifiRun(View view){
        Activity mActivity = new Activity();
        WifiManager wifiMgr = (WifiManager) mActivity.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiMgr.getConnectionInfo();
        savedNetwork=wifiInfo.getSSID();
        mActivity.finish();
    }

    /** Start Service Method */
    public void startService(View view){
        startService(new Intent(getBaseContext(), MyService.class));
    }

    /** Stop Service Method */
    public void stopService(View view) {
        stopService(new Intent(getBaseContext(), MyService.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(msg, "The onStart() event");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(msg, "The onResume() event");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(msg , "The onPause() event");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(msg, "The onStop() event");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(msg, "The onDestroy() event");
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
}
