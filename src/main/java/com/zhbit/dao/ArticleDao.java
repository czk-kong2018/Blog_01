package com.zhbit.dao;

import com.zhbit.dto.*;
import com.zhbit.entity.Article;
import org.apache.ibatis.annotations.*;

import java.util.*;

public interface ArticleDao {

    @Select("select url from article where article_id=#{article_id}")
    String getUrlByArticleId(@Param("article_id") int article_id);
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

    /**
     * 首页操作
     * @Author
     * @param tag
     * @param begin
     * @param end
     * @return
     * @throws Exception
     */



    List<IndexArticle2> getIndexArticle2(@Param("tag") String tag, @Param("begin")int begin, @Param("end")int end)throws Exception;

    /**
     * 后台管理所需
     * @Author
     */
    @Select("select article_id,title,url,create_time from article where own_id=#{user_id} order by article_id limit #{from},#{count}")
     List<Article> articleManage(int from,int count,String userName);


    @Select("select a.*,b.user_name from article as a,user_message as b where article_id=#{article_id} and a.own_id=b.user_id")
     Article getArticle(@Param("article_id") int article_id);

     @Delete("delete from article where article_id=#{article_id}")
    void delete(@Param("article_id") int article_id);

     @Update("update article set title=#{title}, article_profile=#{article_profile} where article_id=#{article_id}")
    void updateTitleAndProfile(@Param("title")String title,@Param("article_profile")String article_profile,@Param("article_id")int article_id);


}
