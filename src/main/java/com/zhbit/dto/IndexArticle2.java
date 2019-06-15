package com.zhbit.dto;

import com.zhbit.entity.Article;
import com.zhbit.entity.Tag;
import com.zhbit.entity.UserMessage;

import java.util.List;

public class IndexArticle2 extends Article {

    private UserMessage userMessage;
    private List<Tag> tag;

    public UserMessage getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(UserMessage userMessage) {
        this.userMessage = userMessage;
    }

    public List<Tag> getTag() {
        return tag;
    }

    public void setTag(List<Tag> tag) {
        this.tag = tag;
    }

}
