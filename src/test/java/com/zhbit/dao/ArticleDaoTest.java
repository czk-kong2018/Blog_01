package com.zhbit.dao;

import com.zhbit.dto.ArticleToPage;
import com.zhbit.dto.UserArticle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class ArticleDaoTest {

    @Autowired
    private ArticleDao ad;


    @Test
    public void getALLArticleByUserName() {
//        List<UserArticle> x = ad.getALLArticleByUserName("梅老板");
//        for (int i = 0; i < x.size(); i++) {
//            System.out.println(x.get(i));
//        }

        String s="java多线程";
        System.out.println(s.hashCode());
    }

    @Test
    public void getArticleByArticleId(){
        List<ArticleToPage> articleToPage = ad.getArticleToPage(0,10,"阿扎尔");
        for (int i = 0; i < articleToPage.size(); i++) {
            System.out.println(articleToPage.get(i));
        }
    }
}