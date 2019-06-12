package com.zhbit.dao;

import com.zhbit.entity.Login;
import com.zhbit.enums.LoginEnum;
import com.zhbit.util.UuidUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class RegisterDaoTest {

    @Autowired
    private RegisterDao registerDao;
    @Test
    public void insertUser() {
        Login login=new Login();
        login.setPwd("123");
        login.setEmail("12314@qq.com");
        int i = registerDao.insertUser(login);
        System.out.println(i);
    }

    @Test
    public void getUserByEmail(){
        System.out.println(LoginEnum.PWDLENGTH.toMap());
        System.out.println(LoginEnum.SUCCESS.toMap());
    }
}