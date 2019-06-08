package com.zhbit.entity;

import java.util.Date;

//未使用到 父评论和数据库的映射
public class FatherComment {
    private int comment_id;
    private int article_id;
    private Date create_time;
    private String content;
    private int click_like;
    private int click_unlike;
    private String response_user;
    private int author_id;

    @Override
    public String toString() {
        return "FatherComment{" +
                "comment_id=" + comment_id +
                ", article_id=" + article_id +
                ", create_time=" + create_time +
                ", content='" + content + '\'' +
                ", click_like=" + click_like +
                ", click_unlike=" + click_unlike +
                ", response_user='" + response_user + '\'' +
                ", author_id=" + author_id +
                '}';
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getResponse_user() {
        return response_user;
    }

    public void setResponse_user(String response_user) {
        this.response_user = response_user;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }
}
