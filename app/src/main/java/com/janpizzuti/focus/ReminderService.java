package com.janpizzuti.focus;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;

public class ReminderService extends IntentService {

    private SharedPreferences sp = getSharedPreferences(MainActivity.SHARED_PREFERENCES_ID, Context.MODE_PRIVATE);
    public final String reminder_action = getResources().getString(R.string.ACTION_GET_OFF_REMINDER);

    public ReminderService() {
        super("ReminderService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (reminder_action.equals(action)) {
                handleActionGetOffReminder();
            }
        }
    }


    private void handleActionGetOffReminder() {
        if (sp.getInt(MainActivity.SWITCH_KEY, Context.MODE_PRIVATE) != 0) {
            Integer minutes = sp.getInt(MainActivity.SPINNER_KEY, 0);
            Integer milliseconds = minutes * 6000;
            try {
                Thread.sleep(milliseconds.longValue());
            } catch (InterruptedException e) {

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
