package com.zhbit.dao;

import com.zhbit.entity.Tag;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class TagDaoTest {

    @Autowired
    private TagDao tagDao;
    @Test
    public void getAllTags() {
        Tag[] userTags = tagDao.getUserTags(1);
        for (int i = 0; i < userTags.length; i++) {
            System.out.println(userTags[i]);
        }
    }
}