package com.zhbit.entity;

import java.util.Date;

public class Article {
    private int article_id;

    @Override
    public String toString() {
        return "Article{" +
                "article_id=" + article_id +
                ", user_name='" + user_name + '\'' +
                ", title='" + title + '\'' +
                ", article_profile='" + article_profile + '\'' +
                ", url='" + url + '\'' +
                ", click_like=" + click_like +
                ", click_unlike=" + click_unlike +
                ", watch=" + watch +
                ", create_time=" + create_time +
                ", own_id=" + own_id +
                '}';
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    private String user_name; //文章拥有者的名字　根据own_id得到

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    private String title;
    private String article_profile;
    private String url;
    private int click_like;
    private int click_unlike;
    private int watch;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArticle_profile() {
        return article_profile;
    }

    public void setArticle_profile(String article_profile) {
        this.article_profile = article_profile;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getClick_like() {
        return click_like;
    }

    public void setClick_like(int click_like) {
        this.click_like = click_like;
    }

    public int getClick_unlike() {
        return click_unlike;
    }

    public void setClick_unlike(int click_unlike) {
        this.click_unlike = click_unlike;
    }

    public int getWatch() {
        return watch;
    }

    public void setWatch(int watch) {
        this.watch = watch;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public int getOwn_id() {
        return own_id;
    }

    public void setOwn_id(int own_id) {
        this.own_id = own_id;
    }

    private Date create_time;
    private int  own_id;
}
