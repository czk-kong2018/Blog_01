package com.zhbit.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum  ChildCommentEnum {
        CONTENTLENGTH("50","内容长度超过限制"),
        COMMENTNULL("51","文章不存在或已被删除"),
        FATHERNULL("52","父评论不存在或者已被删除"),
        CONTENTNULL("53","评论内容不能为空");



    private String errorCode;
        private String message;

        ChildCommentEnum(String errorCode, String message) {
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
