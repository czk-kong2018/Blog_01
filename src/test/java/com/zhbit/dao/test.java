package com.zhbit.dao;

import com.zhbit.aop.Config;
import com.zhbit.aop.LoggerData;
import com.zhbit.entity.Article;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)

public class test {
    @Autowired
    private LoggerData loggerData;

    static final Logger log = LoggerFactory.getLogger(test.class);
    @Test
    public void testAopAnnotation() {

        String s="http://localhost:8081/source/markdown/41560404834482.md";

        System.out.println( s.replaceAll("http://localhost:8081","usr"));
//        log.info("test start");
//        loggerData.perform();
//        log.info("test  end");
    }

}
