package com.zhbit.dao;

import com.zhbit.entity.UserMessage;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMessageDao {

    /**
     * 根据 用户名获取用户id
     * @param user_name
     * @return
     */
    @Select("select user_id from user_message where user_name=#{user_name}")
    int getUserId(@Param("user_name") String user_name);

    @Select("select user_id,user_name,head_portrait,user_profile from user_message where user_name=#{user_name}")
    UserMessage getUserMessageByUserName(@Param("user_name")String user_name);


    int insertUserMessage(@Param("user_id")int user_id,@Param("user_name") String user_name, @Param("head_portrait") String head_portrait);

}
