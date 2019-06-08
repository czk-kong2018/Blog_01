package com.zhbit.entity;

import java.util.Date;

public class ChildComment {
    private int child_comment_id;
    private int article_id;
    private Date create_time;
    private String content;
    private String response_user;
    private String author_name;
    private int comment_id;

    @Override
    public String toString() {
        return "ChildComment{" +
                "child_comment_id=" + child_comment_id +
                ", article_id=" + article_id +
                ", create_time=" + create_time +
                ", content='" + content + '\'' +
                ", response_user='" + response_user + '\'' +
                ", author_name='" + author_name + '\'' +
                ", comment_id='" + comment_id + '\'' +
                '}';
    }

    public int getChild_comment_id() {
        return child_comment_id;
    }

    public void setChild_comment_id(int child_comment_id) {
        this.child_comment_id = child_comment_id;
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

    public String getResponse_user() {
        return response_user;
    }

    public void setResponse_user(String response_user) {
        this.response_user = response_user;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }



}
