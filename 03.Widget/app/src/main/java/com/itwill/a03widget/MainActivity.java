package com.itwill.a03widget;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    TextView textView2;
    EditText editText;
    Button button;
    ImageButton imgBtn;
    ProgressBar progressBar;
    ToggleButton toggleButton;
    ImageView imageView;
    CheckBox checkBox;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int d = R.drawable.w;

        textView = (TextView)findViewById(R.id.textView);
        textView2 = (TextView)findViewById(R.id.textView2);
        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);
        imgBtn = (ImageButton) findViewById(R.id.imageButton);
        progressBar = (ProgressBar ) findViewById(R.id.progressBar);
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
        imageView = (ImageView) findViewById(R.id.imageView2);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        //event처리
        button.setOnClickListener(new ButtonListener());
        imgBtn.setOnClickListener(new View.OnClickListener() {

            boolean trun = true;

            @Override
            public void onClick(View v) {
                ImageButton eventSource = (ImageButton)v;


                if(trun){
                    eventSource.setImageResource(R.drawable.plane);
                    trun = false;
                }else{
                    eventSource.setImageResource(R.drawable.ball);
                    trun = true;
                }
            }
        });

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    progressBar.setVisibility(View.VISIBLE);
                }else{
                    //progressBar.setVisibility(View.INVISIBLE);  //안보이고 레이아웃 유지
                    progressBar.setVisibility(View.GONE);  //안보이고 레이아웃도 제거

                }
            }
        });

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    textView.setText("ACTION_DOWN");
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    textView.setText("ACTION_UP");
                }else if(event.getAction()== MotionEvent.ACTION_MOVE){
                    textView.setText("ACTION_MOVE  "+event.getX()+", "+event.getY());
                }
                return true;   //다음 이벤트 동작 가능 여부
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                LinearLayout ll = (LinearLayout) findViewById(R.id.activity_main);
                switch (checkedId){
                    case R.id.radioButton3:
                        //ll.setBackgroundResource(R.drawable.plane);
                        ll.setBackgroundColor(Color.CYAN);
                        break;
                    case R.id.radioButton4:
                        //ll.setBackgroundResource(R.drawable.w);
                        ll.setBackgroundColor(getResources().getColor(R.color.myColor));
                        break;

                }

            }
        });

    }


    public class ButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            String nameStr = editText.getText().toString();
            textView.setText(nameStr);
        }
    }



}
