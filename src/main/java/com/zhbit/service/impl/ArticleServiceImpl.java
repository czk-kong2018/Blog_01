package com.zhbit.service.impl;

import com.zhbit.dao.ArticleDao;
import com.zhbit.dto.ArticleToPage;
import com.zhbit.dto.UserArticle;
import com.zhbit.entity.Article;
import com.zhbit.service.interfaces.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;


    public List<UserArticle> getALLArticleByUserName(String user_name) {
        List<UserArticle> list = articleDao.getALLArticleByUserName(user_name);
        return list;
    }

    /**
     * 用户那显示的所有文章列表
     * @param current_page
     * @param OnePageCount
     * @param user_name
     * @return
     */
    public List<ArticleToPage> getArticeByPage(int current_page, int OnePageCount, String user_name) {
        int from=(current_page-1)*OnePageCount;
        int count=OnePageCount;
        List<ArticleToPage> articleList = articleDao.getArticleToPage(from, count, user_name);
        return articleList;
    }


    public Article getArticleByArticleId(int article_id){
        Article article = articleDao.getArticleByArticleId(article_id);
        return article;
    }
}
