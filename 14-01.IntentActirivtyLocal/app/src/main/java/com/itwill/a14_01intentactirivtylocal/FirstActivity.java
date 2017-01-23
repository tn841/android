package com.itwill.a14_01intentactirivtylocal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    public void xxx(View v){
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), SecondActivity.class);
        intent.putExtra("extraData", "동해물과");
        intent.putExtra("age", 123);

        Student s = new Student("id", "myName", 233434, true);

        intent.putExtra("student", s);

        startActivity(intent);
        //startService(intent);
        //sendBroadcast(intent);
    }
}
