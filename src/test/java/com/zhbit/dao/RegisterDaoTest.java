package com.zhbit.dao;

import com.zhbit.entity.Login;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class RegisterDaoTest {

    @Autowired
    private RegisterDao registerDao;
    @Test
    public void insertUser() {
        Login login=new Login();
        login.setLogin_name("admin");
        login.setPwd("123");
        login.setEmail("12314@qq.com");
        int i = registerDao.insertUser(login);
        System.out.println(i);
    }

    @Test
    public void getUserByEmail(){
        Integer userByEmail = registerDao.getUserIdByEmail("xx@qq.com");
        System.out.println(userByEmail);
    }
}