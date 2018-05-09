package com.janpizzuti.focus;

import android.app.Application;
import android.content.Intent;
import android.content.IntentFilter;

public class FocusApplication extends Application {
    private ScreenUnlockReceiver mScreenUnlockReceiver;

    @Override
    public void onCreate() {
        super.onCreate();
        mScreenUnlockReceiver = new ScreenUnlockReceiver();
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_USER_PRESENT);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);

        getApplicationContext().registerReceiver(mScreenUnlockReceiver, intentFilter);

    }
}
