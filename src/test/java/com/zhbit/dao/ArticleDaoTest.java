package com.zhbit.dao;

import com.zhbit.dto.ArticleToPage;
import com.zhbit.dto.UserArticle;
import com.zhbit.entity.Article;
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

    }

    @Test
    public void getArticleByArticleId(){
        List<ArticleToPage> articleToPage = ad.getArticleToPage(0,10,"阿扎尔");
        for (int i = 0; i < articleToPage.size(); i++) {
            System.out.println(articleToPage.get(i));
        }
    }
    @Test
    public void publishArticle(){
        List<ArticleToPage> a = ad.getArticleToPage(1, 10, "梅老板");
        for (ArticleToPage userArticle:a) {
            System.out.println(userArticle);
        }
    }
}