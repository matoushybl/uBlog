package com.mat.hyb.ublog.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.mat.hyb.ublog.utility.PreferenceProvider;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EReceiver;

/**
 * Created by matous on 26.4.14 for uBlog.
 */
@EReceiver
public class BootReceiver extends BroadcastReceiver {

    @Bean
    PreferenceProvider provider;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (provider.isReminderEnabled()) {

        }
    }
}
