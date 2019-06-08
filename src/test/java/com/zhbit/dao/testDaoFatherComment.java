package com.zhbit.dao;

import com.zhbit.dto.FatherCommentFront;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class testDaoFatherComment {

    @Autowired
    private CommentDao CommentDao;
    @Test
    public void getComments() {
        List<FatherCommentFront> comments = CommentDao.getCommentByPage(1,0,4);
        for (int i = 0; i < comments.size(); i++) {
            System.out.println(comments.get(i));
        }

    }
}