package com.zhbit.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum  LoginEnum {
    PWDLENGTH("10","密码长度不正确"),
    SUCCESS("11","登录成功"),
    PWDERROR("12","密码不正确"),
    EMAILNOTEXIST("13","邮箱不存在"),
    EMAILERROR("14","邮箱格式不正确"),
    ISNULL("15","邮箱或密码不能为空或者空串");
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
