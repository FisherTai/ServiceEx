package com.example.serviceex;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyService extends Service {

    private MediaPlayer player;
    private static final String TAG = "MyService";

    @Override
    public void onCreate() {
        super.onCreate();
        player = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
        Log.d(TAG, "onCreate: Service id = "+this.getClass().getSimpleName()+"."+this.hashCode());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        player = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
        player.setLooping(true);
        player.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        player.stop();
        Log.d(TAG, "onDestroy: Service id = "+this.getClass().getSimpleName()+"."+this.hashCode());
    }
}
