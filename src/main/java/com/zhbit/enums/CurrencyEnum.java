package com.zhbit.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用的枚举
 */
public enum  CurrencyEnum {
    JSONISNULL("4","json不能为空或者空串"),
    COMMITSUCCESS("5","提交成功"),
    COOKIDEFALSIFY("6","伪造cookie");
    private String errorCode;
    private String message;

    CurrencyEnum(String errorCode, String message) {
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
