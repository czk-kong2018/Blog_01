package com.zhbit.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum  LoginEnum {
    PWDLENGTH("20","密码长度不正确"),
    SUCCESS("21","登录成功"),
    PWDERROR("22","密码不正确"),
    EMAILNOTEXIST("23","邮箱不存在"),
    EMAILERROR("24","邮箱格式不正确"),
    ISNULL("25","邮箱或密码不能为空或者空串"),
    YZMERROR("26","验证码错误");
    private String errorCode;
    private String message;

    LoginEnum(String errorCode, String message) {
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
