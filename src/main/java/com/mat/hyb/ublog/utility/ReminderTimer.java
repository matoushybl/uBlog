package com.mat.hyb.ublog.utility;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.SystemService;

import java.util.Calendar;

/**
 * Created by matous on 26.4.14 for uBlog.
 */
@EBean
public class ReminderTimer {

    private static final String ACTION = "com.mat.hyb.ublog.display";

    @SystemService
    AlarmManager manager;

    @Bean
    PreferenceProvider provider;

    @RootContext
    Context context;

    private PendingIntent startIntent;

    @AfterInject
    void init() {
        startIntent = PendingIntent.getBroadcast(context, 0, new Intent(ACTION), PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public void enable() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, provider.getHour());
        calendar.set(Calendar.MINUTE, provider.getMinute());
        manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, startIntent);
    }

    public void disable() {
        manager.cancel(startIntent);
    }
}
