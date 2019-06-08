package com.zhbit.dao;

import com.zhbit.entity.Login;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface RegisterDao {
    /**
     * 注册成功后创建用户
     * @param login
     * @return
     */
    int insertUser(@Param("login") Login login);

    /**
     * 根据 email获取用户ID
     * @param email
     * @return
     */
    @Select("select login_id from login where email=#{email}")
    Integer getUserIdByEmail(@Param("email") String email);
}
