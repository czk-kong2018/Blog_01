package com.zhbit.service.impl;

import com.zhbit.dao.LoginDao;
import com.zhbit.entity.Login;
import com.zhbit.service.interfaces.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginDao loginDao;
    public Login getLoginByEmail(String email) {
        Login login = loginDao.getLoginByEmail(email);
        return login;
    }
}
