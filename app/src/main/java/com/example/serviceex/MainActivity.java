package com.example.serviceex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonStart;
    private Button buttonStop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonStart = findViewById(R.id.buttonStart);
        buttonStop = findViewById(R.id.buttonStop);

        buttonStop.setOnClickListener(this);
        buttonStart.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.buttonStart:
                    startService(new Intent(this,MyService.class));
                break;
            case R.id.buttonStop:
                    stopService(new Intent(this,MyService.class));
                break;

        }
    }
}
