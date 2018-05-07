package com.janpizzuti.focus;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ScreenUnlockReceiver extends BroadcastReceiver {

    public static final String TAG = "Broadcast Receiver";
    public static final String USER_PRESENT = "USER_PRESENT";
    public static final String USER_NOT_PRESENT = "USER_NOT_PRESENT";
    public static final String SPINNER = "SPINNER";
    public static final String SWITCH = "SWITCH";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: Understand wheter a broadcast receiver is needed and how to implement it
        /**
         *  ¯\_(ツ)_/¯
         */
        Log.d(TAG, intent.getAction());

        Intent myIntent = new Intent(context, ReminderService.class);
        switch (intent.getAction()){
            case "android.intent.action.USER_PRESENT": myIntent.putExtra(USER_PRESENT, USER_PRESENT);
            case "android.intent.action.SCREEN_OFF": myIntent.putExtra(USER_NOT_PRESENT, USER_NOT_PRESENT);
        };



    }



}
