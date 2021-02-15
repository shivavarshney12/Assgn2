package com.example.assgn2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import android.util.Log;
public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "My Receiver Class";
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");
        String action = intent.getAction();
        Toast.makeText(context, action, Toast.LENGTH_LONG).show();
        Log.d("My Receiver Class", "onReceive: " + intent.getStringExtra("time_zone"));
        Log.d("My Receiver Class", "onReceive: Airplane mode: " + intent.getBooleanExtra("state", false));
    }
}




