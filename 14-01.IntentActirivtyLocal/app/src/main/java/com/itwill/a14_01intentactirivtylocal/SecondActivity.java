package com.itwill.a14_01intentactirivtylocal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Log.e("extradata",bundle.getString("extraData"));
        Log.e("age",bundle.getInt("age")+"");
        Log.e("student", (Student)bundle.getSerializable("student")+"");

    }

    public void xxx(View view){
        switch(view.getId()){
            case R.id.secondFinish:
                this.finish();
                break;
            case R.id.startFirstA:
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), FirstActivity.class);
                startActivity(intent);
                break;
        }
    }
}
