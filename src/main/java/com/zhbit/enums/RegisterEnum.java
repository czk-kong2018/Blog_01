package com.zhbit.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum  RegisterEnum {
    PWDLENGTH("11","密码长度不正确"),
    EMAILERROR("12","邮箱格式不正确"),
    SENDSUCCESS("13","发送邮件成功"),
    ISNULL("14","邮箱或者密码为空"),
    EMAILEXIST("15","该邮箱已注册"),
    URLTIMEOUT("16","连接已失效"),
    REGISTERSUCCESS("17","注册成功");
    private String errorCode;
    private String message;

    RegisterEnum(String errorCode, String message) {
        this.errorCode=errorCode;
        this.message=message;
    }
    private String getMessage(){
        return message;
    }
    private String getCode(){
        return errorCode;
    }

    @JsonValue
    public Map<String, String> toMap() {
        Map<String,String> map = new HashMap<String, String>();
        map.put(getCode(),getMessage());
        return map;
    }
}
