package com.zhbit.dao;

import com.zhbit.entity.Login;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface LoginDao {

    /**
     * 根据账户邮箱获取登录账户信息
     * @param email
     * @return
     */
    @Select("select login_id,pwd from login where email=#{email}")
    Login getLoginByEmail(@Param("email") String email);




}
