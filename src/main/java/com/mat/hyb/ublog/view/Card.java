package com.mat.hyb.ublog.view;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.mat.hyb.ublog.R;
import com.mat.hyb.ublog.activity.AddPostActivity_;
import com.mat.hyb.ublog.entity.Post;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;
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

    @StringRes
    String shareTitle;

    @StringRes
    String hide;

    @StringRes
    String show;

    private OnCardDeleted onCardDeleted = new OnCardDeleted() {
        @Override
        public void onDeleted() {

        }
    };

    private Post post;

    private PopupMenu popupMenu;

    public Card(Context context) {
        super(context);
        initView();
    }

    public Card(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    @AfterViews
    void init() {
        popupMenu = new PopupMenu(getContext(), popup);
        popupMenu.inflate(R.menu.card_menu);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
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
                    case R.id.share:
                        share();
                        return true;
                    case R.id.hide:
                        hide(item);
                        return true;
                }
                return false;
            }
        });
    }

    private void hide(MenuItem item) {
        if (item.getTitle().equals(hide)) {
            item.setTitle(show);
            title.setVisibility(INVISIBLE);
            date.setVisibility(INVISIBLE);
            content.setVisibility(INVISIBLE);
        } else {
            item.setTitle(hide);
            title.setVisibility(VISIBLE);
            date.setVisibility(VISIBLE);
            content.setVisibility(VISIBLE);
        }
    }

    @Click(R.id.popup)
    void showPopup() {
        popupMenu.show();
    }

    private void share() {
        String toShare = post.getTitle().toUpperCase() + "\n" + post.getDate() + ", " + post.getTime() + "\n\n"
                + post.getContent();
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, toShare);
        getContext().startActivity(Intent.createChooser(intent, shareTitle));
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

    public void setOnCardDeleted(OnCardDeleted onCardDeleted) {
        this.onCardDeleted = onCardDeleted;
    }

    public interface OnCardDeleted {
        void onDeleted();
    }
}
