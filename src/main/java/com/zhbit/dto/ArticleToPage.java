package com.zhbit.dto;

import java.util.Arrays;
import java.util.List;

/**
 * 个人主页中分页  显示的文章信息
 * 文章所需的信息
 */
public class ArticleToPage {

    private int article_id;
    private UserArticle userArticles;
    private int articleCount; //文章总数 用于分页
    @Override
    public String toString() {
        return "ArticleToPage{" +
                "article_id=" + article_id +
                ", userArticles=" + userArticles +
                ", articleCount=" + articleCount +
                '}';
    }

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }


    public UserArticle getUserArticles() {
        return userArticles;
    }

    public void setUserArticles(UserArticle userArticles) {
        this.userArticles = userArticles;
    }

    public int getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(int articleCount) {
        this.articleCount = articleCount;
    }

}
