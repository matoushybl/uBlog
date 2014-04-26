package com.mat.hyb.ublog.entity;

import org.brightify.torch.annotation.Entity;
import org.brightify.torch.annotation.Id;

/**
 * Created by matous on 25.4.14 for uBlog.
 */
@Entity
public class Post {

    @Id
    public Long getId() {
        return id;
    }

    private Long id;

    private String title;

    private String date;

    private String time;

    private String content;

    private long millis;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getMillis() {
        return millis;
    }

    public void setMillis(long millis) {
        this.millis = millis;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
