package com.mat.hyb.ublog.activity;

import android.preference.PreferenceActivity;

import com.mat.hyb.ublog.fragment.SettingsFragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;

/**
 * Created by matous on 26.4.14 for uBlog.
 */
@EActivity
public class SettingsActivity extends PreferenceActivity {

    @AfterViews
    void init() {
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment()).commit();
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @OptionsItem(android.R.id.home)
    void home() {
        this.finish();
    }
}