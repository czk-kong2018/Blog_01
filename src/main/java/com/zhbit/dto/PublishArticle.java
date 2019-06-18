package com.zhbit.dto;

import com.zhbit.entity.Tag;
import java.util.*;
public class PublishArticle {
    private int article_id;
    private String content; //内容
    private String title; //标题
    private int  own_id; //作者id
    private int edid; //编辑id

    public int getEdid() {
        return edid;
    }

    public void setEdid(int edid) {
        this.edid = edid;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }

    private List<String> tagList;

    @Override
    public String toString() {
        return "PublishArticle{" +
                "article_id=" + article_id +
                ", content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", own_id=" + own_id +
                ", tagList=" + tagList +
                '}';
    }

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getOwn_id() {
        return own_id;
    }

    public void setOwn_id(int own_id) {
        this.own_id = own_id;
    }


}
