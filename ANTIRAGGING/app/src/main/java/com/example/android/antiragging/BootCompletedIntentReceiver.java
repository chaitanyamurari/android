package com.example.android.antiragging;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

/**
 * Created by chait on 7/18/2017.
 */

public class BootCompletedIntentReceiver extends BroadcastReceiver {

Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {


            IntentFilter filter =new IntentFilter(Intent.ACTION_CAMERA_BUTTON);
            MediaButton_Receiver mediaReceiver = new MediaButton_Receiver();
            context.registerReceiver(mediaReceiver, filter);
            Log.d("event", "boot event captured");
        }
    }

}
