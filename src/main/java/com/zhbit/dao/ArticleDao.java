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
     * 查询用户的所有文章
     */
    List<UserArticle> getALLArticleByUserName(@Param("user_name")String user_name);


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
