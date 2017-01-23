package com.itwill.toastdialog;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void xxx(View view) {
        int bId = view.getId();
        switch (bId) {
            case R.id.toastB:
                Toast newToast=Toast.makeText(
                        getApplicationContext(),
                        "안녕",
                        Toast.LENGTH_SHORT);
                newToast.show();
                break;
            case R.id.dialogB:
                /*
                Dialog생성시Activity 참조변수필요
                 */
                AlertDialog.Builder adb=new AlertDialog.Builder(this);
                adb.setTitle("종료");
                adb.setMessage("종료하시겠습니까?");
                adb.setIcon(android.R.drawable.ic_dialog_alert);
                adb.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            setTitle("예");
                        /*
                        Activity종료
                         */
                        MainActivity.this.finish();
                    }
                });
                adb.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setTitle("아니요");
                    }
                });
                adb.setNeutralButton("중립", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setTitle("중립");
                    }
                });

                AlertDialog ad = adb.show();

                break;


        }
    }
}
