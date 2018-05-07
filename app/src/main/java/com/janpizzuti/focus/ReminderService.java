package com.janpizzuti.focus;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class ReminderService extends IntentService {

    private static final String ACTION_GET_OFF_REMINDER = "com.janpizzuti.focus.action.ACTION_START_TIMER_FOR_REMINDER";


    private static final String EXTRA_SPINNER_VALUE = "com.janpizzuti.focus.extra.SPINNER_VALUE";
    private static final String EXTRA_SWITCH_STATE = "com.janpizzuti.focus.extra.SWITCH_STATE";

    public ReminderService() {
        super("ReminderService");
    }


    public static void startActionGetOffReminder(Context context, String spinner_value, String switch_state) {
        Intent intent = new Intent(context, ReminderService.class);
        intent.setAction(ACTION_GET_OFF_REMINDER);
        intent.putExtra(EXTRA_SPINNER_VALUE, spinner_value);
        intent.putExtra(EXTRA_SWITCH_STATE, switch_state);
        context.startService(intent);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_GET_OFF_REMINDER.equals(action)) {
                final String spinner_value = intent.getStringExtra(EXTRA_SPINNER_VALUE);
                final String switch_state = intent.getStringExtra(EXTRA_SWITCH_STATE);
                handleActionGetOffReminder(spinner_value, switch_state);
            }
        }
    }


    private void handleActionGetOffReminder(String spinner_value, String switch_state) {
        // TODO: Handle action
        throw new UnsupportedOperationException("Not yet implemented");
    }


}
