package com.example.serviceex;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String Intent_FROM ="From";
    private static final String TAG = "MainActivity";
    private Intent mIntent,bSIntent ;

    private ServiceConnection myServiceConnection;
    private BindService mBindService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnStartMyService).setOnClickListener(this);
        findViewById(R.id.btnStartBindService).setOnClickListener(this);
        findViewById(R.id.btnplay).setOnClickListener(this);
        findViewById(R.id.btnUnBind).setOnClickListener(this);

        mIntent = new Intent(MainActivity.this,MyService.class);
        mIntent.putExtra(Intent_FROM,TAG);
        bSIntent = new Intent(MainActivity.this, BindService.class);
        bSIntent.putExtra(Intent_FROM,TAG);

        myServiceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.d(TAG, "onServiceConnected: ");
                mBindService = ((BindService.MyBindClass)service).getService();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.d(TAG, "onServiceDisconnected: ");
                mBindService = null;
            }
        };

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            //Start方式的操作
            case R.id.btnStartMyService:
                if (!MyService.isOpen) {
                    startService(mIntent);
                }else {
                    stopService(mIntent);
                }
                break;
            //Bind方式的操作
            case R.id.btnStartBindService:
                bindService(bSIntent, myServiceConnection, Service.BIND_AUTO_CREATE);
                break;
            case R.id.btnplay:
                if(BindService.isBind){
                    mBindService.playerStar();
                }
                break;
            case R.id.btnUnBind:
                if(BindService.isBind){
                   unbindService(myServiceConnection);
                    mBindService = null;
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(BindService.isBind){
            unbindService(myServiceConnection);
        }
    }
}
