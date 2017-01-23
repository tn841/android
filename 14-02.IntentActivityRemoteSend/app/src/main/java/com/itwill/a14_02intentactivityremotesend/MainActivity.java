package com.itwill.a14_02intentactivityremotesend;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Bitmap sendImg; //Parcelable함.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendImg = BitmapFactory.decodeResource(getResources(),R.drawable.text);
    }

    public void xxx(View view){
        Intent intent = new Intent();
        //intent.setData(Uri.parse("tel:020120120"));
        intent.putExtra("bitmap", sendImg);
        intent.setAction("com.itwill.a14_02intentactivityremotereceive.TEST_ACTION");

        startActivity(intent);
    }
}
