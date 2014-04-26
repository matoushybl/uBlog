package com.mat.hyb.ublog.receiver;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;

import com.mat.hyb.ublog.R;
import com.mat.hyb.ublog.activity.MainActivity_;

import org.androidannotations.annotations.EReceiver;
import org.androidannotations.annotations.SystemService;
import org.androidannotations.annotations.res.StringRes;

/**
 * Created by matous on 26.4.14 for uBlog.
 */
@EReceiver
public class TimerReceiver extends BroadcastReceiver {

    private static final String ACTION = "com.mat.hyb.ublog.display";
    private static final int ID = 5;

    @SystemService
    NotificationManager manager;

    @StringRes
    String notificationText;

    @StringRes
    String appName;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() != null && intent.getAction().equals(ACTION)) {
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                    MainActivity_.intent(context).get().setFlags(Intent.FLAG_ACTIVITY_NEW_TASK), 0);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
            builder.setSmallIcon(R.drawable.ic_ab);
            builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(),
                    R.drawable.ic_ab));
            builder.setContentText(notificationText);
            builder.setContentTitle(appName);
            builder.setContentIntent(pendingIntent);
            builder.setAutoCancel(true);
            manager.notify(ID, builder.build());
        }
    }
}
