package com.mat.hyb.ublog.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.mat.hyb.ublog.R;
import com.mat.hyb.ublog.activity.AddPostActivity_;
import com.mat.hyb.ublog.entity.Post;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import org.brightify.torch.TorchService;

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

    @ViewById
    ImageView popup;

    @Click(R.id.popup)
    void showPopup() {
        PopupMenu menu = new PopupMenu(getContext(), popup);
        menu.inflate(R.menu.card_menu);
        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.edit:
                        AddPostActivity_.intent(getContext()).id(post.getId()).start();
                        return true;
                    case R.id.delete:
                        TorchService.torch().delete().entity(post);
                        onCardDeleted.onDeleted();
                        return true;
                }
                return false;
            }
        });
        menu.show();
    }

    public interface OnCardDeleted {
        void onDeleted();
    }

    private OnCardDeleted onCardDeleted = new OnCardDeleted() {
        @Override
        public void onDeleted() {

        }
    };

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
        if (getResources() != null) {
            padding = getResources().getDimensionPixelSize(R.dimen.card_padding);
        }
        setPadding(padding, padding, padding, padding);
    }

    public void setPost(final Post post) {
        this.post = post;
        title.setText(post.getTitle());
        date.setText(post.getDate() + ", " + post.getTime()); // TODO reconsider adding time
        content.setText(post.getContent());
    }

    public Post getPost() {
        return post;
    }

    public void setOnCardDeleted(OnCardDeleted onCardDeleted) {
        this.onCardDeleted = onCardDeleted;
    }
}
