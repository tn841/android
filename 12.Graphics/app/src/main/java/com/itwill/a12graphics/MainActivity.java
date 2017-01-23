package com.itwill.a12graphics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    MyView myView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //case1
        myView = new MyView(getApplicationContext());

        setContentView(myView);

        //case2
        myView = (MyView) findViewById(R.id.myView);



    }
}
