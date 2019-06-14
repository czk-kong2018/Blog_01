package com.zhbit.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用的枚举
 */
public enum  CurrencyEnum {
    JSONISNULL("40","提交数据不符要求"),
    COMMITSUCCESS("41","提交成功"),
    COOKIDEFALSIFY("42","cookie无效");
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
