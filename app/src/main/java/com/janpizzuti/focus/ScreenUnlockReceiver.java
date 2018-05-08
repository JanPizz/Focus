package com.janpizzuti.focus;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;

public class ScreenUnlockReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();

        String myAction = context.getResources().getString(R.string.ACTION_GET_OFF_REMINDER);
        Intent myIntent = new Intent(context, ReminderService.class);

        if (action != null) {
            switch (action) {
                case "android.intent.action.USER_PRESENT":
                    myIntent.setAction(myAction);
                    context.startService(myIntent);
                case "android.intent.action.SCREEN_OFF":
                    context.stopService(myIntent);
            }
        }
    }
}
