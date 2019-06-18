package com.zhbit.service.interfaces;

import com.zhbit.dto.ArticleToPage;
import com.zhbit.dto.IndexArticle;
import com.zhbit.dto.IndexArticle2;
import com.zhbit.dto.UserArticle;
import com.zhbit.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleService {


    /**
     * 根据文章id查询文章
     */
     Article getArticleByArticleId(int article_id);

    /**
     * 查询用户的所有文章
     */
    List<UserArticle> getALLArticleByUserName(String user_name);


    /**
     * 查询用户的所有文章
     */
    List<Article> getALLArticleByUserId(int user_id);

    /**
     * 分页查询用户的文章
     * ＠OnePageCount  一页展示多少文章
     */

    List<ArticleToPage> getArticeByPage(int current_page, int OnePageCount, String user_name);

    /**
     * 插入文章
     */
    void insertArticle(Article article);


    /**
     * 获取首页文章
     * @param tag
     * @param num
     * @Author   拔锋
     * @return
     */
    List<IndexArticle> getIndexArticle(String tag, int num);
    List<IndexArticle2> getIndexArticle2(String tag, int num);


    /**
     * 后台管理所需
     * @Authro  应钊
     */
     List<Article> articleManage(int currentPage, int onePageCount, String userName);
     void delete(int articleID);


}
