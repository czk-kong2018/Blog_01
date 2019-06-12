package com.zhbit.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum  RegisterEnum {
    PWDLENGTH("16","密码长度不正确"),
    EMAILERROR("17","邮箱格式不正确"),
    SENDSUCCESS("18","发送邮件成功"),
    ISNULL("19","邮箱或者密码为空"),
    EMAILEXIST("20","该邮箱已注册"),
    URLTIMEOUT("21","连接已失效"),
    REGISTERSUCCESS("22","注册成功");
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
