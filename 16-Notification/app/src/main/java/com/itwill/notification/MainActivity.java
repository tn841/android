package com.itwill.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void xxx(View view){
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        switch(view.getId()){
            case R.id.notify:

                Notification.Builder notificationBuilder = new Notification.Builder(getApplicationContext());
                notificationBuilder.setAutoCancel(true);
                notificationBuilder.setSmallIcon(android.R.drawable.ic_notification_overlay);
                notificationBuilder.setContentTitle("알림창 테스트");
                notificationBuilder.setContentText("알림창 테스트 중입니다.");
                notificationBuilder.setDefaults(Notification.DEFAULT_ALL);
                notificationBuilder.setSubText("subText");

                Intent intent = new Intent(getApplicationContext(), MyNotificationActivity.class);
                notificationBuilder.addAction(android.R.drawable.stat_notify_chat, "알림창을 테스하는 중 이므로 놀라지마세요.", PendingIntent.getActivity(getApplicationContext(), 0, intent, 0));

                Notification notification = notificationBuilder.build();
                notificationManager.notify(1, notification);
                break;
            case R.id.cancle:
                notificationManager.cancel(1);
                break;
        }

    }
}
