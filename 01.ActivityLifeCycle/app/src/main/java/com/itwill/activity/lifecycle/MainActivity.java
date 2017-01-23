package com.itwill.activity.lifecycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    public MainActivity(){
        Log.e("MainActivity","생성자");
        Log.w("MainActivity","생성자");
        Log.i("MainActivity","생성자");
        Log.d("MainActivity","생성자");
        Log.v("MainActivity","생성자");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("MainActivity","onCreate");
        Log.w("MainActivity","onCreate");
        Log.i("MainActivity","onCreate");
        Log.d("MainActivity","onCreate");
        Log.v("MainActivity","onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("MainActivity","onStart");
        Log.w("MainActivity","onStart");
        Log.i("MainActivity","onStart");
        Log.d("MainActivity","onStart");
        Log.v("MainActivity","onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("MainActivity","onRestart");
        Log.w("MainActivity","onRestart");
        Log.i("MainActivity","onRestart");
        Log.d("MainActivity","onRestart");
        Log.v("MainActivity","onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("MainActivity","onResume");
        Log.w("MainActivity","onResume");
        Log.i("MainActivity","onResume");
        Log.d("MainActivity","onResume");
        Log.v("MainActivity","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("MainActivity","onPause");
        Log.w("MainActivity","onPause");
        Log.i("MainActivity","onPause");
        Log.d("MainActivity","onPause");
        Log.v("MainActivity","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("MainActivity","onStop");
        Log.w("MainActivity","onStop");
        Log.i("MainActivity","onStop");
        Log.d("MainActivity","onStop");
        Log.v("MainActivity","onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("MainActivity","onDestroy");
        Log.w("MainActivity","onDestroy");
        Log.i("MainActivity","onDestroy");
        Log.d("MainActivity","onDestroy");
        Log.v("MainActivity","onDestroy");
    }
}
