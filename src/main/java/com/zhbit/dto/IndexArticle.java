package com.zhbit.dto;

import com.zhbit.entity.Article;
import com.zhbit.entity.Tag;
import com.zhbit.entity.UserMessage;

public class IndexArticle {
    private int id;
    private Article article;
    private UserMessage userMessage;
    private Tag tag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public UserMessage getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(UserMessage userMessage) {
        this.userMessage = userMessage;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
