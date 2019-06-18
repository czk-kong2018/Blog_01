package com.zhbit.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class LoggerDataImpl implements LoggerData{
    public void perform() {
        System.out.println("hello");
    }
}
