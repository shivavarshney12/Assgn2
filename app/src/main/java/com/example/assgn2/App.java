package com.example.assgn2;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class App extends Application {
    public static final String ch_ID="Foreground service channel";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotification();
    }
    public void createNotification() {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) {
            NotificationChannel sc=new NotificationChannel(ch_ID,
                         "Foreground Service",
                        NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager m=getSystemService(NotificationManager.class);
        m.createNotificationChannel(sc);


        }
    }
}
