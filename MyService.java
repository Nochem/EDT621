package com.exist.doesnt.g.myfirstapp;

import android.app.Activity;
import android.app.Service;
import android.content.*;
import android.media.RingtoneManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.*;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.widget.Toast;
import android.util.Log;
import android.net.*;
//import android.app.AlertDialog;
//import android.content.DialogInterface;
//import android.os.Bundle;
//import android.view.View;
//import android.support.v7.app.ActionBarActivity;

import android.support.v7.app.NotificationCompat;


public class MyService extends Service {

    private static String input = "Got to the service";
    public Context context = this;
    public Handler handler = null;
    public static Runnable runnable = null;
    public static String reminderText="???????";
    public static String savedNetwork="??????";

    public static void setReminder(String msg){
        input = msg;
    }




    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
       return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Let it continue running until it's stopped
        // I HAVE A TON OF QUESTIONS ABOUT WHAT THIS IS DOING
        Log.d(input, "The onStartCommand() service event");
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
        ConnectivityManager check = (ConnectivityManager)
                this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //Network[] networks = check.getAllNetworks();


        Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        final NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setSmallIcon(R.drawable.icon) //TODO: replace with an actual picture
                .setContentTitle("Reminder:")
                .setContentText(reminderText) //THis will have to come from the database
                .setAutoCancel(true)
                .setSound(uri)
                .build();
        Activity mActivity = new Activity();
        WifiManager wifiMgr = (WifiManager) mActivity.getSystemService(Context.WIFI_SERVICE);
        final WifiInfo wifiInfo = wifiMgr.getConnectionInfo();



        handler = new Handler();
        runnable = new Runnable(){
            public void run(){
                Toast.makeText(context, input, Toast.LENGTH_LONG).show();
                Log.d(input, "REMINDER!!!");


                    if (savedNetwork!=null){
                        if (savedNetwork==wifiInfo.getSSID()){
                            if (wifiInfo.getRssi() == 0) {
                                mBuilder.notify();
                            }
                        }
                    } else {
                        if (wifiInfo.getRssi() == 0) {
                            mBuilder.notify();
                        }
                    }






                handler.postDelayed(runnable, 10000); // Every ~10s (@ 10000)
            }
        };
        Log.d(input, "handler inside onStartCommande()");
        handler.postDelayed(runnable, 10000); // Seems like it runs immediately at start of service
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
        Log.d(input, "The onDestroy() service event");
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }


}
