package com.itwill.mp3player;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class Mp3PlayerService extends Service {

    MediaPlayer mediaPlayer;


    public Mp3PlayerService() {
        Log.e("Mp3PlayerService", "생성자");
    }


    @Override
    public void onCreate() {
        super.onCreate();
        ServiceStatus.status = 0;
        Log.e("Mp3PlayerService", "onCreate");
        mediaPlayer = new MediaPlayer();




        /****************** Notification ********************/
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder notificationBuilder = new Notification.Builder(getApplicationContext());
        notificationBuilder.setAutoCancel(true);
        notificationBuilder.setSmallIcon(android.R.drawable.ic_media_play);
        notificationBuilder.setContentTitle("City of stars");
        notificationBuilder.setContentText("lala land OST.");
        notificationBuilder.setDefaults(Notification.DEFAULT_LIGHTS);
        notificationBuilder.setSubText("now playing..");

        Intent intent = new Intent(getApplicationContext(), MP3PlayerActivity.class);
        notificationBuilder.addAction(android.R.drawable.stat_notify_chat, "", PendingIntent.getActivity(getApplicationContext(), 0, intent, 0));

        Notification notification = notificationBuilder.build();
        notificationManager.notify(1, notification);
        /**************************************/


    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("Mp3PlayerService", "onStartCommand");


        /*******************/

        String action = intent.getAction();
        Log.e("MP3player Service", action);
        if(action.equals("PLAY")){
            if(ServiceStatus.status == 0){
                try {
                    mediaPlayer.setDataSource("/storage/0000-0000/City Of Stars (From  La La Land  Soundtrack)_Ryan Gosling & Emma Ston.mp3");  //소스설정
                    mediaPlayer.prepare();  //준비완료
                    mediaPlayer.start();    //play시작
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if(ServiceStatus.status == 1){
                //이미 재생중
                Log.e("msg", "이미재생중.");
            }else if(ServiceStatus.status ==2){
                mediaPlayer.start();
            }
            ServiceStatus.status = 1;

        }
        else if(action.equals("PAUSE")){
            if(ServiceStatus.status == 0){
                Log.e("msg", "이미 멈춰있음.");
            }else if(ServiceStatus.status == 1){
                if(mediaPlayer != null) {
                    mediaPlayer.pause();
                }
            }else if(ServiceStatus.status ==2){
                Log.e("msg", "이미 멈춰있음.");
            }

            ServiceStatus.status = 2;


        }

        /********* progress bar 처리 **********/
        int curPosition = mediaPlayer.getCurrentPosition();
        int duration = mediaPlayer.getDuration();
        mediaPlayer.seekTo(duration);
        Log.e("curPoaition", curPosition+"");
        Log.e("duration", duration+"");
        /*******************/

        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("Mp3PlayerService", "onDestroy");
        NotificationManager notificationManager=
                (NotificationManager)getApplicationContext()
                        .getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(1);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
