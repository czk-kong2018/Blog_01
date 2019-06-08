package com.zhbit.dao;

import com.zhbit.entity.ChildComment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.*;
public interface ChildCommentDao {

    /**
     * 根据文章id,查询所有子评论
     */
    @Select("select child_comment_id,article_id,create_time,content,response_user,author_name," +
            "comment_id from child_comment where comment_id=#{article_id}")
    List<ChildComment> getChildCommentByArticleId(@Param("article_id") int article_id);

    /**
     * 根据父评论id 获取子评论
     */
    @Select("select child_comment_id,article_id,create_time,content,response_user,author_name," +
            "comment_id from child_comment where comment_id=#{comment_id}")
    List<ChildComment> getChildCommentByCommentId(@Param("comment_id") int comment_id);

    /**
     * 根据父评论id 获取子评论总数
     */
    @Select("select count(*) from child_comment where comment_id=#{comment_id}")
    int getChildNumByCommentId(@Param("comment_id")int comment_id);



    /**
     * 根据文章id查询文章子评论总数
     * @return
     */
    @Select("select count(*) from child_comment where article_id=#{article_id}")
    int getChildCommentCountByArticleId(@Param("article_id")int article_id);


    /**
     * 写着玩的测试  忽略
     * @param article_id
     * @param Max_comment_id
     * @param Min_comment_id
     * @return
     */
    @Select("select child_comment_id,article_id,create_time,content,response_user,author_name," +
            "comment_id from child_comment where article_id=#{article_id} and comment_id>=#{Min_comment_id} and comment_id<=#{Max_comment_id}")
    List<ChildComment> getChildCommentByArticleIdAndFather(@Param("article_id") int article_id,
                                                           @Param("Max_comment_id")int Max_comment_id,
                                                           @Param("Min_comment_id")int Min_comment_id);



    /**
     * 插入子评论
     * @param childComment
     */
    int commitComment(@Param("childComment") ChildComment childComment);
}
