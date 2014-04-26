package com.mat.hyb.ublog.activity;

import android.app.Activity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.mat.hyb.ublog.R;
import com.mat.hyb.ublog.adapter.CardAdapter;

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

    @AfterViews
    void init() {
        adapter.setOnItemsChanged(new CardAdapter.OnItemsChanged() {
            @Override
            public void onItemsChanged() {
                listView.setAdapter(adapter);
                showOrHideTutorial();
            }
        });
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
        init();
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
