package com.zhbit.dto;

import com.zhbit.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户文章实体类,用于传输用户所有文章
 */
public class UserArticle {
    private int article_id;
    @Autowired
    private Article article;  //实体类的属性不满足 需要一些额外的信息

    private String head_portrait;
    private String tag_name;

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getHead_portrait() {
        return head_portrait;
    }

    public void setHead_portrait(String head_portrait) {
        this.head_portrait = head_portrait;
    }


    @Override
    public String toString() {
        return "UserArticle{" +
                "article_id=" + article_id +
                ", article=" + article +
                ", head_portrait='" + head_portrait + '\'' +
                ", tag_name='" + tag_name + '\'' +
                '}';
    }

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }
}
