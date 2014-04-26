package com.mat.hyb.ublog.activity;

import android.app.Activity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.mat.hyb.ublog.R;
import com.mat.hyb.ublog.adapter.CardAdapter;
import com.mat.hyb.ublog.entity.Post;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;
import org.brightify.torch.TorchService;

import java.util.List;

@OptionsMenu(R.menu.main)
@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {

    @ViewById
    ListView listView;

    @ViewById
    TextView tutorial;

    private List<Post> posts;
    private CardAdapter adapter;

    @AfterViews
    void init() {
        posts = TorchService.torch().load().type(Post.class).list();
        adapter = new CardAdapter(this, R.layout.card, posts);
        listView.setAdapter(adapter);
        if (posts.size() > 0) {
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
