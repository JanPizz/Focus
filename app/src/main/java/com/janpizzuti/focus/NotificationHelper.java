package com.janpizzuti.focus;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class NotificationHelper extends ContextWrapper {
    public static final String channelID = "FocusChannel";
    public static final String channelName = "Get off reminder";
    private NotificationManager mManager;

    public NotificationHelper(Context base) {
        super(base);
        createChannel();
    }

    public void createChannel() {
        NotificationChannel channel =  new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);
        channel.enableLights(true);
        channel.enableVibration(true);

        getManager().createNotificationChannel(channel);
    }

    public NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }

        return mManager;
    }

    public NotificationCompat.Builder sendNotification(String title, String messagge) {
        Log.d("SUGOISUGOISUGOI", "NOTIFICATION SENT");
        return new NotificationCompat.Builder(getApplicationContext(), channelID)
                .setContentTitle(title)
                .setContentText(messagge)
                .setSmallIcon(R.drawable.ic_launcher_foreground) //TODO: Set app icon
                .setPriority(NotificationCompat.PRIORITY_HIGH);

    }
}
