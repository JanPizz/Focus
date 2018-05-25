package com.janpizzuti.focus;

import android.app.Activity;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class ReminderService extends IntentService {



    //private SharedPreferences sp = getSharedPreferences(getResources().getString(R.string.sharedPrefId), Context.MODE_PRIVATE);
    //public final String reminder_action = getResources().getString(R.string.ACTION_GET_OFF_REMINDER);

    public ReminderService() {
        super("ReminderService");
        Log.d("SUGOISUGOISUGOI", "SERVICE CALLED");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        final String reminder_action = getResources().getString(R.string.ACTION_GET_OFF_REMINDER);
        Log.d("SUGOISUGOISUGOI", "HANDLE INTENT CALLED");
        if (intent != null) {
            final String action = intent.getAction();
            if (reminder_action.equals(action)) {
                SharedPreferences sp = getSharedPreferences("com.janpizzuti.focus.SHARED_PREFERENCES", Context.MODE_PRIVATE);
                handleActionGetOffReminder(sp);
            }
        }
    }


    private void handleActionGetOffReminder(SharedPreferences sp) {
        Log.d(MainActivity.TAG, "HANDLE ACTION CALLED");
        if (sp.getInt("com.janpizzuti.focus.SWITCH_KEY", Context.MODE_PRIVATE) != 0) {
            Integer minutes = sp.getInt(getResources().getString(R.string.spinnerKey), 0);
            Integer milliseconds = minutes * 60 * 1000;
            NotificationHelper nh = new NotificationHelper(this);
            String title = getString(R.string.Get_off_reminder_title);
            String mins = minutes.toString();
            try {
                Thread.sleep(milliseconds.longValue());
            } catch (InterruptedException e) {
                Log.d(MainActivity.TAG, "CATCH");
                nh.sendNotification(getString(R.string.something_is_wrong_error),
                        getString(R.string.something_is_wrong_message));
            }

            Notification notification;

            if (minutes == 0) {
                notification = nh.sendNotification(title, getString(R.string.reminder_zero));
                Log.d(MainActivity.TAG,"notification time zero");
            } else {
                notification = nh.sendNotification(title, String.format(getString(R.string.reminder_some_minutes), mins));
                Log.d(MainActivity.TAG,"notification time some minutes");
            }

            NotificationManager manager = nh.getManager();
            manager.notify(1, notification);




        }
    }

}
