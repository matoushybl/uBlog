package com.mat.hyb.ublog.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.mat.hyb.ublog.entity.Post;
import com.mat.hyb.ublog.view.Card;
import com.mat.hyb.ublog.view.Card_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.brightify.torch.TorchService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matous on 26.4.14 for uBlog.
 */
@EBean
public class CardAdapter extends BaseAdapter {

    @RootContext
    Context context;

    private List<Post> posts = new ArrayList<Post>();
    private List<Card> cards = new ArrayList<Card>();

    @Override
    public int getCount() {
        return cards.size();
    }

    @Override
    public Card getItem(int position) {
        return cards.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return cards.get(position);
    }

    public void refresh() {
        posts = TorchService.torch().load().type(Post.class).list();
        cards = new ArrayList<Card>();
        for (Post post : posts) {
            Card card = Card_.build(context);
            card.setPost(post);
            cards.add(card);
        }
    }
}
