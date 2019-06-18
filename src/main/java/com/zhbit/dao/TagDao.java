package com.zhbit.dao;

import com.zhbit.entity.Tag;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public  interface TagDao {


    /**
     * 获取数据库里所有的标签
     * @return
     */
    Tag[] getAllTags();

    /**
     * 根据用户名获取用户文章的所有标签
     */
    Tag[] getUserTags(@Param("user_id") int user_id);



    void insertArticleTag(@Param("article_id") int article_id,@Param("tag_id") int tag_id);

    /**
     * 删除文章所有标签
     * @param article_id
     */
    @Delete("delete from article_tag where article_id=#{article_id}")
    void deleteAllByArticleId(@Param("article_id")int article_id);

}
