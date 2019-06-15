package com.zhbit.dao;

import com.zhbit.dto.ArticleToPage;
import com.zhbit.dto.PublishArticle;
import com.zhbit.dto.UserArticle;
import com.zhbit.entity.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.*;

public interface ArticleDao {


    /**
     * 根据文章id查询文章,用户名传过来只是为了生成url
     */
    @Select("select a.*,b.user_name from article as a,user_message as b where article_id=#{article_id} and a.own_id=b.user_id")
    Article getArticleByArticleId(@Param("article_id") int article_id);

    /**
     * 查询用户的所有文章  通过用户名 带标签
     */
    List<UserArticle> getALLArticleByUserName(@Param("user_name")String user_name);
    /**
     * 查询用户所有文章 通过id  不需要带标签 用于后台管理显示管理文章  暂用
     *
     */
    @Select("select article_id,title,url,create_time from article where own_id=#{user_id}")
    List<Article> getALLArticleByUserId(@Param("user_id") int user_id);

    /**
     * 分页查询用户的文章
     * ＠OnePageCount  一页展示多少文章
     */

     List<ArticleToPage> getArticleToPage(@Param("from") int from,@Param("count") int count,@Param("user_name") String user_name);

    /**
     * 获取这篇文章的评论总数
     */
    @Select("select (select count(*)  from child_comment as a where a.article_id=#{article_id})+(select count(*)  from comment_on as a where a.article_id=#{article_id})")
    int getCommentCount(@Param("article_id")int article_id);


    /**
     * 上传插入文章
     * @return
     */
    int insertArticle(@Param("article") Article article);


}
