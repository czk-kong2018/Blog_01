package com.zhbit.dao;

import com.zhbit.entity.Tag;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

}
