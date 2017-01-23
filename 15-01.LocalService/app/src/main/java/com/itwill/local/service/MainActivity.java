package com.itwill.local.service;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void xxx(View view){
        switch(view.getId()){
            case R.id.startBtn:
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), CustomService.class);
                startService(intent);
                break;
            case R.id.stopBtn:
                Intent intent1 = new Intent();
                intent1.setClass(getApplicationContext(), CustomService.class);
                stopService(intent1);
                break;
        }
    }
}
