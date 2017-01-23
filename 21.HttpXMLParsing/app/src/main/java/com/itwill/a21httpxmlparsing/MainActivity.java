package com.itwill.a21httpxmlparsing;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {
    GridView gridView;
    ArrayList<Picture> globalPictureList = new ArrayList();
    ProgressDialog progressDialog;

    public static final int PICTURE_LIST = 0;
    public static final int PICTURE_ONE = 1;
    public static final int ERROR = 9;

    BaseAdapter pictureAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //Toast.makeText(getApplicationContext(), "사진 로딩중.. 잠시만 기다려주세요.", Toast.LENGTH_SHORT).show();

        gridView = (GridView)findViewById(R.id.gridView);
        pictureAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return globalPictureList.size();
            }

            @Override
            public Object getItem(int position) {
                return globalPictureList.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = getLayoutInflater().inflate(R.layout.grid_child_view, null);

                Picture picture = (Picture)getItem(position);
                TextView pictureTV = (TextView)view.findViewById(R.id.pictureTV);
                ImageView pictureIV = (ImageView)view.findViewById(R.id.pictureIV);

                pictureTV.setText(picture.getTitle());
                pictureIV.setImageBitmap(picture.getBitImg());
                return view;
            }
        };

        gridView.setAdapter(pictureAdapter);

        progressDialog = new ProgressDialog(MainActivity.this);  //인자로 Activity를 넣어야한다. getContext()절대 안됨
        progressDialog.setMessage("잠시만 기다려주세요.");
        progressDialog.setCancelable(false);
        progressDialog.show();


        new Thread(){
            @Override
            public void run() {
                getXMLParsing();
            }
        }.start();
    }//onCreate()

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            switch(msg.what){
                case PICTURE_LIST:
                    ArrayList<Picture> receivePictureList = (ArrayList<Picture>) msg.obj;
                    globalPictureList = receivePictureList;

                    /*
                    adapter의 DataSource가 변경되었다고 알려주어야한다.
                     */
                    pictureAdapter.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(), "사진 로딩 완료", Toast.LENGTH_SHORT).show();

                    break;
                case PICTURE_ONE:
                    Picture p = (Picture)msg.obj;
                    globalPictureList.add(p);
                    pictureAdapter.notifyDataSetChanged();
                    //Toast.makeText(getApplicationContext(), "사진 "+globalPictureList.size()+"개 로딩,", Toast.LENGTH_SHORT).show();
                    break;
                case ERROR:
                    Exception e = (Exception) msg.obj;
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    break;
            }
        }


    };


    private void getXMLParsing(){
        String flickerUrlStr = "http://api.flickr.com/services/feeds/photos_public.gne?id=26648248@N04";
        try{
            URL filckerUrl = new URL(flickerUrlStr);
            InputStream in= filckerUrl.openStream();

            InputStreamReader isr = new InputStreamReader(in);
            InputSource inputSource = new InputSource(isr);

            ArrayList<Picture> pictureList = new ArrayList<Picture>();

            /*
            XML DOMparser객체 생성
             */
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder domparser = dbf.newDocumentBuilder();
            /*
            Document 객체 생성
             */
            Document document = domparser.parse(in);
            Log.e("dom",document.toString());

            NodeList entryN = document.getElementsByTagName("entry");
            for(int i=0; i<entryN.getLength(); i++){
                Element entryE = (Element) entryN.item(i);

                NodeList titleN = entryE.getElementsByTagName("title");
                Element titleE = (Element) titleN.item(0);
                String titleStr = titleE.getFirstChild().getNodeValue();

                NodeList linkNL = entryE.getElementsByTagName("link");
                Element linkE = (Element)linkNL.item(1);
                String imgURLStr = linkE.getAttribute("href");

                String imgURLStr_s = imgURLStr.replace("_b.","_s.");
                URL imgURL = new URL(imgURLStr_s);
                InputStream imgin = imgURL.openStream();
                Bitmap bitImg = BitmapFactory.decodeStream(imgin);


                Picture p = new Picture(titleStr, imgURLStr_s, bitImg);
                pictureList.add(p);


                Log.e("titleE", titleStr);
                Log.e("link", imgURLStr_s);
                Log.e("bitmapImg",bitImg.toString());

                //picture 1개씩 바로바로전송
                progressDialog.dismiss();
                Message msg = handler.obtainMessage(MainActivity.PICTURE_ONE, p);
                handler.sendMessage(msg);


            }//for

            /*
            Handler를 이용하여 pictureList msg전송
             */
            Message msg = handler.obtainMessage(MainActivity.PICTURE_LIST, pictureList);
            //handler.sendMessage(msg);

        }catch(Exception e){
            e.printStackTrace();

            Message msg = handler.obtainMessage(MainActivity.ERROR, e);
            handler.sendMessage(msg);
        }
    }//getParsingXML()



}
