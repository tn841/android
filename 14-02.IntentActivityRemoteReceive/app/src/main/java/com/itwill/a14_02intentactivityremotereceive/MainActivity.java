package com.itwill.a14_02intentactivityremotereceive;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);

        Intent intent = getIntent();
        if(intent.getAction().equals("com.itwill.a14_02intentactivityremotereceive.TEST_ACTION")    ){
            Bitmap receiveImg = getIntent().getExtras().getParcelable("bitmap");
            imageView.setImageBitmap(receiveImg);
        }else{

        }


    }
}
