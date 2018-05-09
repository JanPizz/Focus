package com.janpizzuti.focus;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class ScreenUnlockReceiver extends BroadcastReceiver {

    private static String TAG = ScreenUnlockReceiver.class.getSimpleName();


    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d(TAG, "RECEIVER CALLED");
        Toast.makeText(context,"screen unlock", Toast.LENGTH_SHORT).show();

        String action = intent.getAction();

        String myAction = context.getResources().getString(R.string.ACTION_GET_OFF_REMINDER);
        Intent myIntent = new Intent(context, ReminderService.class);

        if (action != null) {
            switch (action) {
                case "android.intent.action.USER_PRESENT":
                    Log.d("SUGOISUGOISUGOI", "USER PRESENT");
                    myIntent.setAction(myAction);
                    context.startService(myIntent);
                    break;
                case "android.intent.action.SCREEN_OFF":
                    Log.d("SUGOISUGOISUGOI", "SCREEN OFF");
                    context.stopService(myIntent);
                    break;
            }
        }
    }
}
