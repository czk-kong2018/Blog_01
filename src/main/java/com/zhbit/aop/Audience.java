package com.zhbit.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Audience {


    static final Logger log = LoggerFactory.getLogger(Audience.class);
    @Pointcut("execution(* com.zhbit.aop.LoggerDataImpl.perform(..))")
    public void perform(){}

    @Before("perform()")
    public void silenceCellPhones() {
        System.out.println("a");
    }

    @After("perform()")
    public void summary() {
        log.info("你好啊");
    }
}
