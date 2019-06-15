package com.zhbit.service.interfaces;

import com.zhbit.entity.Login;

public interface RegisterService {

    Login Register(String loginName, String pwd, String email, String code);

    int insertUser(Login login);

    Integer getUserIdByEmail(String email);
}
