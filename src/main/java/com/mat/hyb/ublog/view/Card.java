package com.mat.hyb.ublog.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import org.androidannotations.annotations.EViewGroup;

/**
 * Created by matous on 26.4.14 for uBlog.
 */
@EViewGroup
public class Card extends LinearLayout {

    public Card(Context context) {
        super(context);
    }

    public Card(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
