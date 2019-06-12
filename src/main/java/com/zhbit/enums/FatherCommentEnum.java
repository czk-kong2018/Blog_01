package com.zhbit.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum FatherCommentEnum {
    CONTENTLENGTH("7","内容长度超过限制"),
    COMMENTNULL("8","文章不存在或已被删除"),
    CONTENTNULL("9","评论内容不能为空");



    private String errorCode;
    private String message;

    FatherCommentEnum(String errorCode, String message) {
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
