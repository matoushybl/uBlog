package com.mat.hyb.ublog.activity;

import android.app.Activity;

import com.mat.hyb.ublog.R;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;

@OptionsMenu(R.menu.main)
@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {

    @OptionsItem
    void settings() {
        SettingsActivity_.intent(this).start();
    }
}
