package com.example.serviceex;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.Nullable;

import static com.example.serviceex.MainActivity.Intent_FROM;

public class BindService extends Service {
    public static boolean isBind = false;
    private MyBindClass myBind;
    private MediaPlayer player;
    private static final String TAG = "BindService";


    @Override
    public void onCreate() {
        super.onCreate();
        player = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
        Log.d(TAG, "onCreate: Service id = "+this.getClass().getSimpleName()+"."+this.hashCode());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: "+intent.getStringExtra(Intent_FROM));
        myBind = new MyBindClass();
        isBind = true;
        return myBind;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        player.stop();
        isBind = false;
        Log.d(TAG, "onDestroy: Service id = "+this.getClass().getSimpleName()+"."+this.hashCode());
    }

    public class MyBindClass extends Binder{
       public BindService getService(){
           return BindService.this;
       }
    }

    public void playerStar(){
        player.setLooping(true);
        player.start();
    }
}
