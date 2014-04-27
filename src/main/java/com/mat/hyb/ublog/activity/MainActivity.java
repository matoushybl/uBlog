package com.mat.hyb.ublog.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import com.mat.hyb.ublog.R;
import com.mat.hyb.ublog.adapter.CardAdapter;
import com.mat.hyb.ublog.utility.PreferenceProvider;
import com.mat.hyb.ublog.utility.ReminderTimer;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

@OptionsMenu(R.menu.main)
@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {

    @ViewById
    ListView listView;

    @ViewById
    TextView tutorial;

    @Bean
    CardAdapter adapter;

    @Bean
    ReminderTimer timer;

    @Bean
    PreferenceProvider provider;

    @AfterViews
    void init() {
        adapter.setOnItemsChanged(new CardAdapter.OnItemsChanged() {
            @Override
            public void onItemsChanged() {
                update();
            }
        });
        if (Build.VERSION.SDK_INT >= 19) {
            Window window = getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            SystemBarTintManager systemBarTintManager = new SystemBarTintManager(this);
            systemBarTintManager.setStatusBarTintColor(getResources().getColor(R.color.base_color));
            systemBarTintManager.setNavigationBarAlpha(0.0f);
            systemBarTintManager.setNavigationBarTintColor(getResources().getColor(android.R.color.black));
            systemBarTintManager.setStatusBarTintEnabled(true);
            systemBarTintManager.setNavigationBarTintEnabled(true);
        }
    }

    void update() {
        adapter.refresh();
        listView.setAdapter(adapter);
        showOrHideTutorial();
    }

    private void showOrHideTutorial() {
        if (adapter.getCount() > 0) {
            tutorial.setVisibility(View.GONE);
        } else {
            tutorial.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        update();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setIcon(R.drawable.ic_ab);
    }

    @OptionsItem
    void add() {
        AddPostActivity_.intent(this).start();
    }

    @OptionsItem
    void settings() {
        SettingsActivity_.intent(this).start();
    }
}
