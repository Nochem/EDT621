package com.exist.doesnt.g.myfirstapp;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.*;
import android.media.RingtoneManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.*;
import android.support.annotation.Nullable;
import android.widget.Toast;
import android.util.Log;
import android.net.*;
import android.support.v7.app.NotificationCompat;


public class MyService extends Service {

    private static String input = "Got to the service";
    public Context context = this;
    public Handler handler = null;
    public static Runnable runnable = null;
    public static String reminderText="????????";
    public static String savedNetwork=null;






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

        Notification note =new Notification();
                startForeground(1, note);
        Log.d(input, "The onStartCommand() service event");
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
        ConnectivityManager check = (ConnectivityManager)
                this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationManager notificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setSmallIcon(R.drawable.icon)
                .setContentTitle("Reminder:")
                .setContentText(reminderText)
                .setAutoCancel(true)
                .setSound(uri);
        final android.app.Notification not =mBuilder.build();
        final NotificationManager notifyMan =(NotificationManager) getSystemService(NOTIFICATION_SERVICE);




        handler = new Handler();
        runnable = new Runnable(){
            public void run(){
                Toast.makeText(context, input, Toast.LENGTH_LONG).show();
                Log.d(input, "REMINDER!!!");

                  notifyMan.notify(2, not);





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
