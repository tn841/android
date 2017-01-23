package com.itwill.a99thread;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView countTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countTV = (TextView)findViewById(R.id.countTV);

        new CountTread().start();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }



    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            /*
                주쓰레드 실행
            */
            switch (msg.what){
                case 0:
                    int recvCount = (Integer) msg.obj;
                    countTV.setText(recvCount+"");
                    break;
            }

        }


    };



    public class CountTread extends Thread{

        int tCount = 0;

        @Override
        public void run() {
            while(true){
                try {
                    Thread.sleep(1);

                    tCount++;

                    //외부 쓰레드에서 주 쓰레드의 객체를 수정할 수 없다.
                    Message msg = new Message();
                    msg.what = 0;
                    msg.obj = tCount;
                    handler.sendMessage(msg);


                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
