package com.mat.hyb.ublog.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import com.mat.hyb.ublog.R;
import com.mat.hyb.ublog.entity.Post;

import org.brightify.torch.TorchService;

import java.util.List;

/**
 * Created by matous on 26.4.14 for uBlog.
 */
public class SettingsFragment extends PreferenceFragment {

    private final static String PREFERENCE_AUTHOR = "authorInfo";
    private final static String PREFERENCE_DELETE = "deleteAll";
    private final static String PREFERENCE_LIBS = "usedLibraries";
    private final static String AUTHOR_G_PLUS_URI = "https://plus.google.com/114245631152542174178/posts";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.main_preferences);
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
                    builder.setMessage("* SystemBarTint\n* AndroidAnnotations\n* Torch");
                    builder.setNegativeButton(getResources().getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    builder.create().show();
                    return true;
                }
            });
        }
    }
}
