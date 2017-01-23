package com.itwill.a13gamethread;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    GameView gameView;
    GameThread gameThread;
    boolean isPlay = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameView=(GameView) findViewById(R.id.gameView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameThread = new GameThread();
        gameThread.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        isPlay = false;
        gameThread = null;
    }

    /********* GameThread **********/
    public class GameThread extends Thread{
        @Override
        public void run() {

            while(isPlay){
                try {
                    Thread.sleep(100);
                    gameView.postInvalidate();  //post 메소드는 handler에게 갱신하라는 것을 알려줌
                }catch(Exception e){
                    e.printStackTrace();
                }
            }

        }
    }
}
