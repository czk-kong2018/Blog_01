package com.zhbit.service.interfaces;

import com.zhbit.entity.Login;

public interface LoginService {

     Login getLoginByEmail(String email);
}
