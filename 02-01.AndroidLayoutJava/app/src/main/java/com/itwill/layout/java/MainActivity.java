package com.itwill.layout.java;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Button myBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myBtn=new Button(this);
        myBtn.setText("난 자바로만든버튼");
        /*
        View를 Activity에 부친다
         */
        this.setContentView(myBtn);
        //event
        myBtn.setOnClickListener(new ButtonClickHandler());
    }
    /*********inner class*************/
    public class ButtonClickHandler implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            myBtn.setText(new Date().toLocaleString());
        }

    }
}
