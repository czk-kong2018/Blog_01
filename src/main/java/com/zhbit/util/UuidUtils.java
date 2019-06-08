package com.zhbit.util;
import java.util.UUID;
public class UuidUtils {

    public static String createUUid(){
       return UUID.randomUUID().toString().replaceAll("-","");
    }
}
