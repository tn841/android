package com.itwill.a12graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by stu on 2017-01-16.
 */

public class MyView extends View{
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e("MyView","onDraw");
        /*
        Canvas canvas : View영역이면서 펜객체( cf> Graphics)
         */
        canvas.drawColor(Color.WHITE);
        //rect
        Paint paint=new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(10,10,110,110,paint);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(10,120,110,220,paint);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
        //line
        canvas.drawLine(0,230,getWidth(),230,paint);
        canvas.drawLine(0,260,getWidth(),260,paint);
        canvas.drawLine(0,290,getWidth(),290,paint);
        paint.setColor(Color.RED);
        canvas.drawLine(0,getHeight()/2,
                getWidth(),getHeight()/2,paint);
        canvas.drawLine(getWidth()/2,
                0,
                getWidth()/2,
                getHeight(),
                paint);
        //circle
        paint.setAntiAlias(true);
        canvas.drawCircle(getWidth()/2,
                getHeight()/2,
                200,
                paint);
        canvas.drawCircle(getWidth()/2,
                getHeight()/2,
                100,
                paint);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(getWidth()/2,
                getHeight()/2,
                50,
                paint);
        //bitmap
        /*
        resource
         */
        Resources resources=getResources();
        Bitmap bitmap1=
                BitmapFactory
                        .decodeResource(resources,R.drawable.penguin1);
        Bitmap bitmap2=
                BitmapFactory
                        .decodeResource(resources,R.drawable.penguin2);
        Bitmap bitmap3=
                BitmapFactory
                        .decodeResource(resources,R.drawable.penguin3);

        canvas.drawBitmap(bitmap1,100,getHeight()-200,null);

        Bitmap scaledBitmap2=Bitmap.createScaledBitmap(bitmap2,
                bitmap2.getWidth()*2,bitmap2.getHeight()*2,false);
        canvas.drawBitmap(scaledBitmap2,300,getHeight()-400,null);
        Bitmap scaledBitmap3=Bitmap.createScaledBitmap(bitmap3,
                bitmap3.getWidth()/2,bitmap3.getHeight()/2,false);
        canvas.drawBitmap(scaledBitmap3,600,getHeight()-100,null);



        /*
            sdcard -> decode -> bitmap으로
            읽기 권한 필요
         */
        String path = Environment.getExternalStorageDirectory().getPath();
        Log.e("sd card path", path);
        String filePath = path+"/map.JPG";
        try{
            FileInputStream fis = new FileInputStream(filePath);
            Bitmap sdcardBitmap = BitmapFactory.decodeStream(fis);
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(sdcardBitmap, getWidth(), getHeight(), true);
            canvas.drawBitmap(scaledBitmap, 0, 0, null);

        }catch(Exception e){
            e.printStackTrace();
        }



        //Bitmap --> encode-->SDCARD image 저장
        try {
            File sdcardFile = Environment.getExternalStorageDirectory();
            //FileOutputStream fout=new FileOutputStream(sdcardFile);
            /*
            AndroidManifest.xml에서 퍼미션사용을 선언해주어야합니다.
            <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
             */
            FileOutputStream fout=
                    new FileOutputStream(sdcardFile.getPath()+"/"+System.currentTimeMillis());
            scaledBitmap2.compress(Bitmap.CompressFormat.JPEG,100,fout);

            FileOutputStream fout2 = new FileOutputStream(sdcardFile.getPath()+"/"+"dmdkdmdk.png");
            scaledBitmap3.compress(Bitmap.CompressFormat.PNG,100,fout2);

            Toast.makeText(
                    getContext(),
                    "file save success!!",
                    Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(
                    getContext(),
                    e.getMessage(),
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

        //SDCARD

    }
}
