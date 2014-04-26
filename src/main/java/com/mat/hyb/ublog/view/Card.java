package com.mat.hyb.ublog.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.mat.hyb.ublog.R;

import org.androidannotations.annotations.EViewGroup;

/**
 * Created by matous on 26.4.14 for uBlog.
 */
@EViewGroup(R.layout.card)
public class Card extends LinearLayout {

    public Card(Context context) {
        super(context);
        initView();
    }

    public Card(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        setBackgroundResource(R.drawable.card);
        setOrientation(VERTICAL);
    }
}
