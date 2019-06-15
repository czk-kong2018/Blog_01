package com.zhbit.util;

public class StringUtils {
    /**
     * 字符串不为空且不是空串
     */
    public static boolean StringNotNullAndEmpty(String s){
        return s!=null&&!s.trim().equals("")?true: false;
    }
}

