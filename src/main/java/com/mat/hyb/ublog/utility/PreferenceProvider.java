package com.mat.hyb.ublog.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

/**
 * Created by matous on 26.4.14 for uBlog.
 */
@EBean
public class PreferenceProvider {

    private static final String MINUTE = "minute";
    private static final String HOUR = "hour";
    private final static String PREFERENCE_NOTIF = "notification";


    @RootContext
    Context context;

    private SharedPreferences preferences;

    public PreferenceProvider(Context context) {
        this.context = context;
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public int getMinute() {
        return preferences.getInt(MINUTE, 0);
    }

    public int getHour() {
        return preferences.getInt(HOUR, 15);
    }

    public void setMinute(int minute) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(MINUTE, minute);
        editor.commit();
    }

    public void setHour(int hour) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(HOUR, hour);
        editor.commit();
    }

    public boolean isReminderEnabled() {
        return preferences.getBoolean(PREFERENCE_NOTIF, false);
    }

}
