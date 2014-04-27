package com.mat.hyb.ublog.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.mat.hyb.ublog.R;
import com.mat.hyb.ublog.entity.Post;
import com.mat.hyb.ublog.utility.DateTimeParser;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;
import org.brightify.torch.TorchService;

/**
 * Created by matous on 26.4.14 for uBlog.
 */
@EActivity(R.layout.add_activity)
public class AddPostActivity extends Activity {

    @ViewById
    EditText title;

    @ViewById
    EditText content;

    @StringRes
    String warning;

    @Bean
    DateTimeParser parser;

    @Extra("id")
    long id = -1;

    private Post post = new Post();

    @AfterViews
    void init() {
        if (id != -1) {
            post = TorchService.torch().load().type(Post.class).id(id);
            title.setText(post.getTitle());
            content.setText(post.getContent());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Activity activity = this;
        LayoutInflater inflater = (LayoutInflater) getActionBar().getThemedContext()
                .getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.action_bar_done_cancel, null);
        view.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        view.findViewById(R.id.done).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (title.getText() != null && content.getText() != null
                        && !title.getText().toString().equals("")) {
                    post.setTitle(title.getText().toString());
                    post.setContent(content.getText().toString());
                    post.setMillis(System.currentTimeMillis());
                    post.setDate(parser.getDate());
                    post.setTime(parser.getTime());
                    TorchService.torch().save().entity(post);
                    finish();
                } else {
                    Toast.makeText(activity, warning, Toast.LENGTH_LONG).show();
                }
            }
        });
        getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM, ActionBar.DISPLAY_SHOW_CUSTOM
                | ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);
        getActionBar().setCustomView(view, new ActionBar.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
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
}
