package com.itwill.a13gamethread;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by itwill on 2017-01-16.
 */

public class GameView extends View {
    Bitmap backBitmap;
    Bitmap planeBitmap;
    Bitmap plainRight;
    Bitmap plainLeft;

    int pX,pY;  //plane위치
    int tX,tY;  //touch위치
    int vWidth; //GameView 너비
    int vHeight;//GameView 높이
    int pWidth, pHeight;
    int direction = 0;  //0은 우측, 1은 좌측

    private void init(){
        backBitmap= BitmapFactory
                .decodeResource(
                        getResources(),
                        R.drawable.game_back);
        plainRight=BitmapFactory
                .decodeResource(
                        getResources(),
                        R.drawable.img3);

       Matrix matrix = new Matrix();
        matrix.setScale(-1, 1);
//      //  matrix.setRotate(180);
        plainLeft = Bitmap.createBitmap(plainRight,0,0,plainRight.getWidth(), plainRight.getHeight(), matrix, true);
//
        planeBitmap = plainRight;

        pWidth = planeBitmap.getWidth();
        pHeight = planeBitmap.getHeight();
        pX=300+pWidth/2;
        pY=200+pHeight/2;

    }
    /*
    View touch(key) event처리
    -->public boolean onTouchEvent(MotionEvent event)재정의
     */
    boolean planeTouch = false;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        tX = (int)event.getX();
        tY = (int)event.getY();
        Log.e("touchEvent------------","start");


        if(event.getAction()==MotionEvent.ACTION_DOWN){
            /*
            비행기영역 터치여부
             */
            if(isPlaneTouch(tX,tY)){
                planeTouch=true;
            }else{
                planeTouch=false;
            }
        }else if(event.getAction()==MotionEvent.ACTION_MOVE){
            if(planeTouch){
                pX=tX;
                pY=tY;
            }

        }else if(event.getAction()==MotionEvent.ACTION_UP){

            planeTouch=false;
        }

        if(!planeTouch) {
            if (tX > pX) {
                //오른쪽
                planeBitmap = plainRight;
                pX += 30;
                direction = 0;
            } else if (tX < pX) {
                //왼쪽
                planeBitmap = plainLeft;
                pX -= 30;
                direction = 1;
            } else {

            }
        }




        invalidate();   // =repaint()


        return true;


    }

    private boolean isPlaneTouch(int tX,int tY){
        boolean isTouch=false;
        if(tX>=pX-pWidth/2 && tX<=pX+pWidth/2){
            if(tY>=pY-pHeight/2 && tY<=pY+pHeight/2){
                isTouch=true;
            }
        }
        return isTouch;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(backBitmap,0,0,null);
        canvas.drawBitmap(planeBitmap,pX-planeBitmap.getWidth()/2,pY-planeBitmap.getHeight()/2,null);

        int randNum = (int)(Math.random()*30);
        Log.e("rand", randNum+"");
        if(randNum%2 ==0){
            direction = 0;
        }
        else{
            direction = 1;
        }

        switch (direction){
            case 0:
                pX += 5+(int)(Math.random()*30);
                break;
            case 1:
                pX -= 5+(int)(Math.random()*30);
                break;
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
       /*
        View size 변경될때마다 호출
         */
        vHeight=h;
        vWidth=w;

        backBitmap = Bitmap
                .createScaledBitmap(
                        backBitmap,
                        vWidth,
                        vHeight,false);

    }

    public GameView(Context context) {
        super(context);
        init();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


}
