package com.zhbit.service.impl;

import com.zhbit.dao.*;
import com.zhbit.dto.ArticleToPage;
import com.zhbit.dto.IndexArticle;
import com.zhbit.dto.IndexArticle2;
import com.zhbit.dto.UserArticle;
import com.zhbit.entity.Article;
import com.zhbit.entity.ChildComment;
import com.zhbit.service.interfaces.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private TagDao tagDao;
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private ChildCommentDao childCommentDao;
    @Autowired
    private NotifyDao notifyDao;
    //根据用户名获取用户所有文章 临时使用  因为有分页代替了
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



    public String getUrlByArticleId(int article_id) {
        return articleDao.getUrlByArticleId(article_id);
    }

    public void updateTitleAndProfile(String title, String article_profile, int article_id) {
        articleDao.updateTitleAndProfile(title,article_profile,article_id);
    }

    public Article getArticleByArticleId(int article_id){
        Article article = articleDao.getArticleByArticleId(article_id);
        return article;
    }

    public void insertArticle(Article article){
        articleDao.insertArticle(article);
    }

    /**
     * @Author
     * @param tag
     * @param num
     * @return
     */

    public List<IndexArticle> getIndexArticle(String tag, int num) {
        try{
            return articleDao.getIndexArticle(tag,num,num+9);
        }catch (Exception e){
            return null;
        }

    }

    public List<IndexArticle2> getIndexArticle2(String tag, int num) {
        try{
            return articleDao.getIndexArticle2(tag,num,num+9);
        }catch (Exception e){
            return null;
        }

    }

    public List<Article> getALLArticleByUserId(int user_id) {
        return  articleDao.getALLArticleByUserId(user_id);
    }

//后台管理部分
    /**
     * @Author
     */
    public List<Article> articleManage(int currentPage, int onePageCount, String userName) {
        return articleDao.articleManage((currentPage-1)*onePageCount, onePageCount, userName);
    }

    @Transactional
    public void delete(int articleID) {
        notifyDao.deleteAllByArticleId(articleID);
        tagDao.deleteAllByArticleId(articleID);
        childCommentDao.deleteChildCommentByArticleId(articleID);
        commentDao.deleteCommentByArticleId(articleID);
        articleDao.delete(articleID);
    }


}
