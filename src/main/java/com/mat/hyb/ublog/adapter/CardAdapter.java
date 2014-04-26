package com.mat.hyb.ublog.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.mat.hyb.ublog.entity.Post;
import com.mat.hyb.ublog.view.Card;
import com.mat.hyb.ublog.view.Card_;

import java.util.List;

/**
 * Created by matous on 26.4.14 for uBlog.
 */
public class CardAdapter extends ArrayAdapter<Post> {

    private List<Post> posts;

    public CardAdapter(Context context, int resource, List<Post> objects) {
        super(context, resource, objects);
        posts = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Card card = (Card) convertView;
        if (card == null) {
            card = Card_.build(getContext());
        }
        card.setPost(posts.get(position));
        return card;
    }
}
