package com.example.nochem.afm;

import android.os.Bundle;
import android.widget.EditText;
import android.app.Activity;
import android.net.wifi.WifiManager;


public class MainActivity extends Activity {

    private String reminderText, currentNetwork, homeNetwork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    EditText input = (EditText) findViewById(R.id.editText);
    WifiManager wifi;

    /* Called by the SAVE button. Starts the program. */
    public void save(){
        reminderText=input.getText().toString();
        currentNetwork=wifi.getConnectionInfo().getSSID(); //TODO: Fix required permissions in manifes
        if (wifi.getConnectionInfo().getRssi() == 0) { //Permissions issue here too
                //reminderAlarm();
        }
    }
}

/*
        homeNetwork=input2.getText().toString();  //Suggested code to make sure it won't go off on all wifis
        if (currentNetwork.equals(homeNetwork)) {
*/
/**
 * This app will give reminderAlarm if the wifi-signal is cut FOR WHATEVER REASON.
 * This app demands that the user leaves the wifi on at all times.
 * This app must be started on the users home network (or whatever network the user wants it to work on)
 * This app could potentially use -alot- of performance if we don't stagger how often it checks the wifi.
 */
