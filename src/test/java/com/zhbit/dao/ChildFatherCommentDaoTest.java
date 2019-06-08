package com.zhbit.dao;

import com.zhbit.entity.ChildComment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class ChildFatherCommentDaoTest {


    @Autowired
    private ChildCommentDao childCommentDao;
    @Test
    public void  getChildCommentByArticleId(){
        ChildComment childComment=new ChildComment();
        childComment.setArticle_id(2);
        childComment.setAuthor_name("阿扎尔");
        childComment.setComment_id(3);
        childComment.setContent("d出现在出现在");
        //childComment.setResponse_user("");
        childCommentDao.commitComment(childComment);
    }
}