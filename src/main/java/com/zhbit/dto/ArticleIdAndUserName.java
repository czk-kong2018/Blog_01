package com.zhbit.dto;

public class ArticleIdAndUserName {
    private String article_id;


    @Override
    public String toString() {
        return "ArticleIdAndUserName{" +
                "article_id='" + article_id + '\'' +
                '}';
    }

    public String getArticle_id() {
        return article_id;
    }

    public void setArticle_id(String article_id) {
        this.article_id = article_id;
    }

}
