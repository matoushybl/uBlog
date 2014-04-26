package com.mat.hyb.ublog.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.mat.hyb.ublog.activity.MainActivity_;

import org.androidannotations.annotations.EReceiver;

/**
 * Created by matous on 26.4.14 for uBlog.
 */
@EReceiver
public class NotificationReceiver extends BroadcastReceiver {

    private static final String ACTION = "com.mat.hyb.ublog.open";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() != null && intent.getAction().equals(ACTION)) {
            MainActivity_.intent(context).start();
        }
    }
}
