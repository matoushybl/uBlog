package com.mat.hyb.ublog.fragment;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.main_preferences);
        Preference reset = findPreference("deleteAll");
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
    }
}
