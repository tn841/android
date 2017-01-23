package com.itwill.intent.remote.google.activity.call;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void xxx(View view){
        switch(view.getId()){
            case R.id.dialerBtn:
                Intent intent1 = new Intent();
                intent1.setAction(Intent.ACTION_DIAL);
                intent1.setData(Uri.parse("tel:51515151515"));

                Uri recvData = intent1.getData();
                String scheme = recvData.getScheme();
                String path = recvData.getPath();

                startActivity(intent1);
                break;
            case R.id.SMSBtn:
                Intent intent2 = new Intent();
                intent2.setAction(Intent.ACTION_SENDTO);
                intent2.setData(Uri.parse("sms:01022318565"));
                intent2.putExtra("sms_body","촉촉한 초코칩");
                startActivity(intent2);
                break;
            case R.id.googleMapBtn:
                Intent intent3 = new Intent();
                intent3.setAction(Intent.ACTION_VIEW);
                intent3.setData(Uri.parse("geo:37.189134, 127.01865215"));
                startActivity(intent3);
                break;
            case R.id.webSearchBtn:
                Intent intent4 = new Intent();
                intent4.setAction(Intent.ACTION_WEB_SEARCH);
                intent4.putExtra("query", "google");
                startActivity(intent4);
                break;
            case R.id.audioPickBtn:
                Intent intent5 = new Intent();
                intent5.setAction(Intent.ACTION_GET_CONTENT);
                //intent5.setType("image/*");
                //intent5.setType("video/*");
                intent5.setType("audio/*");
                startActivity(intent5);
                break;
            case R.id.audioPlayBtn:
                Intent intent6 = new Intent();
                intent6.setAction(Intent.ACTION_VIEW);
                intent6.setDataAndType(Uri.fromFile(new File("/sdcard/14 - Too Long.mp3")),"audio/*");
                startActivity(intent6);
                break;
            case R.id.cameraCaptureBtn:
                Intent intent7 = new Intent();
                intent7.setAction("android.media.action.IMAGE_CAPTURE");
                startActivity(intent7);
                break;

        }
    }
}
