package com.itwill.intent.activity.forresult;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button button, imgBtn;
    TextView textView;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        imgBtn = (Button) findViewById(R.id.googleBtn);
        textView = (TextView) findViewById(R.id.textView);
        imageView = (ImageView) findViewById(R.id.imageView);

        MyOnClick listener = new MyOnClick();

        button.setOnClickListener(listener);
        imgBtn.setOnClickListener(listener);
    }//onCreate()


    public class MyOnClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.button) {
                //데이터를 받아올 액티비티 실행
                Intent intent = new Intent(getApplicationContext(), SubActivity.class);

                //startActivity(intent);    //데이터를 받아올 수 없다.
                startActivityForResult(intent, 0);
            }
            else if(v.getId() == R.id.googleBtn){
                Intent intent = new Intent();
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 1);
            }
        }
    }


    //startActivityForResult에 의해 호출된 Activity가 finish될 때 호출
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode){
            case 0:
                if(resultCode == Activity.RESULT_OK){   // -1
                    String str = (String) data.getExtras().getSerializable("data");
                    Bitmap img = data.getExtras().getParcelable("picture");

                    textView.setText(str);
                    imageView.setImageBitmap(img);
                }else if(resultCode == Activity.RESULT_CANCELED){ // 0

                }
                break;
            case 1:
                if(resultCode == Activity.RESULT_OK){
                    Log.e("here", "im");
                    Bitmap camerImg = data.getExtras().getParcelable("data");
                    imageView.setImageBitmap(camerImg);
                }else if(resultCode == Activity.RESULT_CANCELED){

                }
                break;
        }
    }

}
