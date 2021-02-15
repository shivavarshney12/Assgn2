package com.example.assgn2;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;
import static com.example.assgn2.App.ch_ID;
public class MyService extends Service {
    MediaPlayer myPlayer;
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myPlayer = MediaPlayer.create(this, R.raw.song);
        myPlayer.setLooping(false); // Set looping
    }

    @Override
    public int onStartCommand(Intent intent,int flags, int startId) {
        Intent i=new Intent(this,MainActivity.class);
        PendingIntent pi=PendingIntent.getActivity(this,0,i,0);
        Notification n= new NotificationCompat.Builder(this,ch_ID)
                .setContentTitle("Foreground Service")
                .setContentText("Foregrnd Service")
                .setSmallIcon(R.drawable.ic_android)
                .setContentIntent(pi)
                .build();
        startForeground(1,n);
        /*myPlayer.setOnPreparedListener(new onPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                myPlayer.start();
            }
        });*/
        if(myPlayer.isPlaying()) {
            myPlayer.pause();
            //myPlayer.release();
        }
        myPlayer.start();
        return START_NOT_STICKY;
    }
    @Override
    public void onDestroy() {
        myPlayer.stop();
        myPlayer.release();
        super.onDestroy();
    }
}