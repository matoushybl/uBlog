package com.mat.hyb.ublog;

import android.app.Application;

import com.mat.hyb.ublog.entity.Post;

import org.androidannotations.annotations.EApplication;
import org.brightify.torch.TorchService;

/**
 * Created by matous on 25.4.14 for uBlog.
 */
@EApplication
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        TorchService.with(this).register(Post.class);
    }
}
