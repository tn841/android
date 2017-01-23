package com.itwill.mp3player;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MP3PlayerActivity extends AppCompatActivity {
    ImageButton pauseIB, playIB;
    ImageView imageView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mp3_player);

        pauseIB = (ImageButton) findViewById(R.id.pauseImgBtn);
        playIB = (ImageButton) findViewById(R.id.playImgBtn);

        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        progressBar.setMax(200);

        ButtonHandler bh = new ButtonHandler();

        pauseIB.setOnClickListener(bh);
        playIB.setOnClickListener(bh);

        new Thread(){
            int count = 0;

            @Override
            public void run() {
                try {
                    while (true) {
                        Thread.sleep(100);
                        count++;
                        count = count % 200;
                        progressBar.setProgress(count);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();


    }

    public class ButtonHandler implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.playImgBtn:
                    Intent intent = new Intent();
                    intent.setClass(getApplicationContext(), Mp3PlayerService.class);
                    intent.setAction("PLAY");
                    startService(intent);
                    break;
                case R.id.pauseImgBtn:
                    Intent intent1 = new Intent();
                    intent1.setClass(getApplicationContext(), Mp3PlayerService.class);
                    intent1.setAction("PAUSE");
                    startService(intent1);
                    break;
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        final boolean flag = false;

        if(KeyEvent.KEYCODE_BACK == keyCode){
            //Toast.makeText(getApplicationContext(),"Back. "+ServiceStatus.status, Toast.LENGTH_SHORT).show();
            Log.e("ServiceStatus", ServiceStatus.status+"");

            /*AlertDialog.Builder adb = new AlertDialog.Builder(this);
            adb.setIcon(android.R.drawable.ic_dialog_alert);
            adb.setTitle("종료");
            adb.setMessage("종료하시겠습니까?");
            adb.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            adb.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            adb.show();*/

            //앱 열고 그냥 뒤로가기 할 경우
            if(ServiceStatus.status == 0){
                Toast.makeText(getApplicationContext(), "노래 재생 않고 바로 뒤로갈 경우", Toast.LENGTH_SHORT).show();
                stopService(new Intent(getApplicationContext(), Mp3PlayerService.class));
            }

            //재생 상태일 때는 서비스 유지하고 액티비티만 종료
            if(ServiceStatus.status == 1){
                Toast.makeText(getApplicationContext(), "노래는 계속 재생됩니다.", Toast.LENGTH_SHORT).show();
            }

            //재생 중이 아닐 때는 서비스까지 종료
            if(ServiceStatus.status == 2){
                Toast.makeText(getApplicationContext(), "서비스와 액비티비 모두 종료합니다.", Toast.LENGTH_SHORT).show();
                stopService(new Intent(getApplicationContext(), Mp3PlayerService.class));
            }

        }//if

        return super.onKeyDown(keyCode, event);

    }
}
