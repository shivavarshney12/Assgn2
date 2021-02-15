package com.example.assgn2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.os.Build;
import android.Manifest;
import android.content.pm.PackageManager;
import android.content.IntentFilter;
import android.app.DownloadManager;
import android.widget.Toast;
import androidx.annotation.NonNull;
import android.net.Uri;
import android.content.Context;
import android.os.Environment;


public class MainActivity extends AppCompatActivity {

    MyReceiver mr = new MyReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void start(View v) {
        //startService(new Intent(this,MyService.class));
        ContextCompat.startForegroundService(this,new Intent(this,MyService.class));
    }
    public void stop(View v) {
        stopService(new Intent(this,MyService.class));
    }
    public void download(View v) {

        //if OS is Marshmallow or above, handle runtime permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                //permission denied, request it
                String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                //show popup for runtime permission
                requestPermissions(permissions, 1000);
            }
            else {
                //permission already granted, perform download
                startDownload();
            }
        }
        else {
            //system os is less than marshmallow, perform download
            startDownload();
        }
    }
    private void startDownload() {
        //String url = aUrlEt.getText().toString().trim();
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse("http://faculty.iiitd.ac.in/~mukulika/s1.mp3"));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setTitle("Download");
        request.setDescription("Downloading file...");
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "" + System.currentTimeMillis());
        DownloadManager manager = (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1000) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_DENIED) {
                startDownload();
            }
            else {
                Toast.makeText(this, "Permission denied...!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        this.registerReceiver(mr, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.unregisterReceiver(mr);
    }

}
