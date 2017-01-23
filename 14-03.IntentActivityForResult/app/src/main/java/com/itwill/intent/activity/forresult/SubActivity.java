package com.itwill.intent.activity.forresult;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SubActivity extends AppCompatActivity {
    Button btn1, btn2;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        btn1 = (Button) findViewById(R.id.cancleBtn);
        btn2 = (Button) findViewById(R.id.okBtn);

        MyOnClickListener myOnClickListener = new MyOnClickListener();

        btn1.setOnClickListener(myOnClickListener);
        btn2.setOnClickListener(myOnClickListener);

        bitmap = BitmapFactory.decodeFile("/storage/0000-0000/pizon.jpg");  //storage



    }

    public class MyOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.okBtn:
                    //data저장용 Intent
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("data", "Pizon!");
                    resultIntent.putExtra("picture", bitmap);

                    //Intent 데이터 저장
                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();
                    break;
                case R.id.cancleBtn:
                    setResult(Activity.RESULT_CANCELED, null);
                    finish();
                    break;
            }
        }
    }//MyOnClickListener



}
