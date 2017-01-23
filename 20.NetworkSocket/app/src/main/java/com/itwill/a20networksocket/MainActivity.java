package com.itwill.a20networksocket;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText resultET;
    ImageView imgView;
    Button textB, imgB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultET = (EditText)findViewById(R.id.resultET);
        textB = (Button) findViewById(R.id.textB);
        imgB = (Button) findViewById(R.id.imgB);
        imgView = (ImageView) findViewById(R.id.imageView);

        ButtonHandler buttonHandler = new ButtonHandler();
        textB.setOnClickListener(buttonHandler);
        imgB.setOnClickListener(buttonHandler);
    }


    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    resultET.setText((String)msg.obj);

                    break;
                case 1:
                    Bitmap reciveImg = (Bitmap) msg.obj;
                    imgView.setImageBitmap(reciveImg);
                    break;
                case 2:
                    Exception recvException=(Exception)msg.obj;

                    Toast.makeText(
                            getApplicationContext(),
                            recvException.getMessage(),
                            Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    public class ButtonHandler implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.textB){


                new Thread(){
                    @Override
                    public void run() {
                        try {
                            /** 네트워크 사용을 위한 퍼미션 추가 **/
                            Socket socket = new Socket("naver.com", 80);
                            OutputStream out = socket.getOutputStream();
                            String sendData = "GET /index.html HTTP/1.0\r\n\r\n";
                            out.write(sendData.getBytes());
                            out.flush();

                            InputStream in =  socket.getInputStream();
                            InputStreamReader isr = new InputStreamReader(in);
                            BufferedReader br = new BufferedReader(isr);
                            StringBuffer sb = new StringBuffer();

                            while(true){
                                String readLine = br.readLine();
                                sb.append(readLine+"\n");

                                if (readLine == null) {
                                    break;
                                }
                            }
                            //Log.e("html", sb.toString());
                            Message msg = new Message();
                            msg.what = 0;
                            msg.obj = sb.toString();

                            handler.sendMessage(msg);

                        } catch (IOException e) {
                            e.printStackTrace();
                            Message msg = new Message();
                            msg.what = 2;
                            msg.obj = e;
                            handler.sendMessage(msg);
                        }
                    }
                }.start();


            }
            else if(v.getId() == R.id.imgB){
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            String imgURL = "http://movie.phinf.naver.net/20161123_188/1479862185516tYkKO_JPEG/movie_image.jpg";
                            URL url = new URL(imgURL);
                            InputStream in = url.openStream();
                            Bitmap bitmapImg = BitmapFactory.decodeStream(in);

                            Message msg = new Message();
                            msg.what = 1;
                            msg.obj = bitmapImg;

                            handler.sendMessage(msg);
                            Log.e("asdf", "asdfasd");
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
            }

        }
    }





