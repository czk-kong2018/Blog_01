package com.zhbit.dao;

import com.zhbit.dto.FatherCommentFront;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
public interface CommentDao {

    List<FatherCommentFront>  getComments(@Param("article_id")int article_id);


    /**
     * 提交父评论, 直接评论博主
     * @param comment
     * @param user_id
     * @return
     */
    int commitComment(@Param("comment") FatherCommentFront comment, @Param("user_id") int user_id);

    /**
     * 分页获取评论
     * @param from
     * @param count
     * @param article_id
     * @return
     */
    List<FatherCommentFront> getCommentByPage(@Param("article_id")int article_id, @Param("from") int from, @Param("count") int count);

    /**
     * 获取文章父评论数
     */
    @Select("select count(*)  from comment_on as a where a.article_id=#{article_id}")
    int getFatherCommentCount(@Param("article_id")int article_id);

    /**
     * 删除父评论   根据文章id
     */
    @Delete("delete from comment_on where article_id=#{article_id}")
    void deleteCommentByArticleId(@Param("article_id")int article_id);

}
