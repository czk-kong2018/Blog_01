package com.zhbit.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Audience {
    @Pointcut("execution(* com.zhbit.aop.LoggerDataImpl.perform(..))")
    public void perform(){}

    @Before("perform()")
    public void silenceCellPhones() {
        System.out.println("a");
    }

    @After("perform()")
    public void summary() {
        System.out.println("写会议总结报告");
    }
}
