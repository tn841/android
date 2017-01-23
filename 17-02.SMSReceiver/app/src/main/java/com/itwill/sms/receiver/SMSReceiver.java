package com.itwill.sms.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver {
    public SMSReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        Object[] pdus = (Object[]) intent.getExtras().get("pdus"); //pdus란 네트워크로부터 오는 패키지

        SmsMessage receiveMsg = SmsMessage.createFromPdu((byte[])pdus[0]);
        String address = receiveMsg.getOriginatingAddress();
        String message = receiveMsg.getMessageBody();

        Toast.makeText(context, "Tel : "+address+", 내용 : "+message, Toast.LENGTH_SHORT).show();
    }
}
