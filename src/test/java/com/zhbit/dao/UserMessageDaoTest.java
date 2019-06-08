package com.zhbit.dao;

import com.zhbit.entity.UserMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class UserMessageDaoTest {

    @Autowired
    private UserMessageDao userMessageDao;
    @Test
    public void getUserName() {
//        int user_id = userMessageDao.getUserId("梅老板");
//        System.out.println(user_id);
        UserMessage s = userMessageDao.getUserMessageByUserName("aa");
        System.out.println(s);
    }
    @Test
    public void insertUserMessage(){
        userMessageDao.insertUserMessage(5,"admin","http://localhost:8081/source/image/default.jpg");
    }
}