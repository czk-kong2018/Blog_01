package com.zhbit.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum ArticleEnum {

    SUCCESSPUBLISH("61","文章上传成功"),
    CONTENTLESS("62","博客内容不得少于100字"),
    TAGMAX("63","选择标签不要超过3个");




    private String errorCode;
    private String message;

    ArticleEnum(String errorCode, String message) {
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
