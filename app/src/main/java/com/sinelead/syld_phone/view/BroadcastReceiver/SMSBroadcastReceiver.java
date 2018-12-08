package com.sinelead.syld_phone.view.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SMSBroadcastReceiver extends BroadcastReceiver {
    private EditText editText;

    public SMSBroadcastReceiver(EditText editText) {
        this.editText = editText;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Object[] puds = (Object[]) intent.getExtras().get("pdus");
        if (puds != null)
            for (Object pud : puds) {
                SmsMessage message = SmsMessage.createFromPdu((byte[]) pud);
                String body = message.getMessageBody();
                String sender = message.getOriginatingAddress();
                if (body.contains("验证码")) {
                    Pattern pattern = Pattern.compile("[0-9]+");
                    Matcher matcher = pattern.matcher(body);
                    if (matcher.find()) {
                        String num = matcher.group(0);
                        editText.setText(num);
                    }
                }
            }
        abortBroadcast();
//        Log.i("lifeme", "SMSBroadcastReceiver");
    }
}
