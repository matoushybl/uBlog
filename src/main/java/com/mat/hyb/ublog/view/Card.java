package com.mat.hyb.ublog.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mat.hyb.ublog.R;
import com.mat.hyb.ublog.entity.Post;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by matous on 26.4.14 for uBlog.
 */
@EViewGroup(R.layout.card)
public class Card extends LinearLayout {

    @ViewById
    TextView title;

    @ViewById
    TextView date;

    @ViewById
    TextView content;

    public Card(Context context) {
        super(context);
        initView();
    }

    private Post post;

    public Card(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        setBackgroundResource(R.drawable.card);
        setOrientation(VERTICAL);
        int padding = 0;
        int bottomMargin = 0;
        if (getResources() != null) {
            padding = getResources().getDimensionPixelSize(R.dimen.card_padding);
            bottomMargin = getResources().getDimensionPixelSize(R.dimen.card_margin);
        }
        setPadding(padding, padding, padding, padding);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, bottomMargin);
        setLayoutParams(params);
    }

    public void setPost(Post post) {
        this.post = post;
        title.setText(post.getTitle());
        date.setText(post.getDate()); // TODO reconsider adding time
        content.setText(post.getContent());
    }

    public Post getPost() {
        return post;
    }
}
