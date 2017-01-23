package com.itwill.sms.receiver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


/*
        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    Log.e("3초후 종료","dd");
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();*/
        //finish();

    }
}
