package com.zhbit.service.interfaces;

import com.zhbit.entity.Login;

public interface RegisterService {

    Login Register( String pwd, String email, String code);

    String insertUser(Login login);

    Integer getUserIdByEmail(String email);
}
