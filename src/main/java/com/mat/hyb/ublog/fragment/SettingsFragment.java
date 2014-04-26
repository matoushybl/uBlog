package com.mat.hyb.ublog.fragment;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import com.mat.hyb.ublog.R;
import com.mat.hyb.ublog.entity.Post;
import com.mat.hyb.ublog.utility.PreferenceProvider;
import com.mat.hyb.ublog.utility.PreferenceProvider_;
import com.mat.hyb.ublog.utility.ReminderTimer;
import com.mat.hyb.ublog.utility.ReminderTimer_;

import org.androidannotations.annotations.EFragment;
import org.brightify.torch.TorchService;

import java.util.List;

/**
 * Created by matous on 26.4.14 for uBlog.
 */
@EFragment
public class SettingsFragment extends PreferenceFragment {

    private final static String PREFERENCE_AUTHOR = "authorInfo";
    private final static String PREFERENCE_DELETE = "deleteAll";
    private final static String PREFERENCE_LIBS = "usedLibraries";
    private final static String PREFERENCE_NOTIF = "notification";
    private final static String PREFERENCE_TIME = "notificationTime";
    private final static String AUTHOR_G_PLUS_URI = "https://plus.google.com/114245631152542174178/posts";

    private PreferenceProvider provider;
    private ReminderTimer timer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.main_preferences);
        provider = PreferenceProvider_.getInstance_(getActivity());
        timer = ReminderTimer_.getInstance_(getActivity());
        Preference reset = findPreference(PREFERENCE_DELETE);
        if (reset != null) {
            reset.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    List<Post> posts = TorchService.torch().load().type(Post.class).list();
                    TorchService.torch().delete().entities(posts);
                    return true;
                }
            });
        }

        final CheckBoxPreference notificationCheck = (CheckBoxPreference) findPreference(PREFERENCE_NOTIF);
        final Preference notificationTime = findPreference(PREFERENCE_TIME);
        if (notificationCheck != null && notificationTime != null) {
            notificationTime.setEnabled(notificationCheck.isChecked());
            notificationCheck.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object o) {
                    notificationCheck.setChecked((Boolean) o);
                    notificationTime.setEnabled(notificationCheck.isChecked());
                    if ((Boolean) o) {
                        timer.enable();
                    } else {
                        timer.disable();
                    }
                    return false;
                }
            });
            notificationTime.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    TimePickerDialog dialog = new TimePickerDialog(getActivity(),
                            new TimePickerDialog.OnTimeSetListener() {
                                @Override
                                public void onTimeSet(TimePicker timePicker, int i, int i2) {
                                    provider.setHour(i);
                                    provider.setMinute(i2);
                                    timer.enable();
                                }
                            }, provider.getHour(), provider.getMinute(),
                            DateFormat.is24HourFormat(getActivity())
                    );
                    dialog.show();
                    return false;
                }
            });
        }
        Preference author = findPreference(PREFERENCE_AUTHOR);
        if (author != null) {
            author.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    startActivity(new Intent().setAction(Intent.ACTION_VIEW)
                            .setData(Uri.parse(AUTHOR_G_PLUS_URI)));
                    return true;
                }
            });
        }
        Preference libs = findPreference(PREFERENCE_LIBS);
        if (libs != null) {
            libs.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Used libraries");
                    builder.setMessage("* SystemBarTint\n* AndroidAnnotations\n* Torch\n* FloatLabelLayout");
                    builder.setNegativeButton(getResources().getString(android.R.string.ok),
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            }
                    );
                    builder.create().show();
                    return true;
                }
            });
        }
    }
}
