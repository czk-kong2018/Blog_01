package com.zhbit.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class NotifyDaoTest {

    @Autowired
    private NotifyDao notifyDao;
    @Test
    public void createNotify() {
        notifyDao.createNotify("梅老板","阿扎尔","梅老板@评论了你",1);
    }
}