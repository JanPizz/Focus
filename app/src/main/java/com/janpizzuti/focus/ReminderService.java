package com.janpizzuti.focus;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class ReminderService extends IntentService {

    private SharedPreferences sp = getSharedPreferences(getResources().getString(R.string.sharedPrefId), Context.MODE_PRIVATE);
    public final String reminder_action = getResources().getString(R.string.ACTION_GET_OFF_REMINDER);

    public ReminderService() {
        super("ReminderService");
        Log.d("SUGOISUGOISUGOI", "SERVICE CALLED");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("SUGOISUGOISUGOI", "HANDLE INTENT CALLED");
        if (intent != null) {
            final String action = intent.getAction();
            if (reminder_action.equals(action)) {
                handleActionGetOffReminder();
            }
        }
    }


    private void handleActionGetOffReminder() {
        Log.d("SUGOISUGOISUGOI", "HANDLE ACTION CALLED");
        if (sp.getInt(getResources().getString(R.string.switchKey), Context.MODE_PRIVATE) != 0) {
            Integer minutes = sp.getInt(getResources().getString(R.string.spinnerKey), 0);
            Integer milliseconds = minutes * 6000;
            NotificationHelper nh = new NotificationHelper(this);
            String title = getString(R.string.Get_off_reminder_title);
            String mins = minutes.toString();
            try {
                Thread.sleep(milliseconds.longValue());
            } catch (InterruptedException e) {
                nh.sendNotification(getString(R.string.something_is_wrong_error),
                        getString(R.string.something_is_wrong_message));
            }
            if (minutes == 0) {
                nh.sendNotification(title, getString(R.string.reminder_zero));
            } else {
                nh.sendNotification(title, String.format(getString(R.string.reminder_some_minutes), mins));
            }



        }
    }
/*
    public int onUserPresent(Context context, Intent intent) {
        if (sp.getInt(MainActivity.SWITCH_KEY, Context.MODE_PRIVATE) != 0) {
            Integer minutes = sp.getInt(MainActivity.SPINNER_KEY, 0);
            Integer milliseconds = minutes * 6000;
            try {
                Thread.sleep(milliseconds.longValue());
            } catch (InterruptedException e) {

            }
        }

    }
*/

}
