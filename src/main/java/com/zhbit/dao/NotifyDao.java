package com.zhbit.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface NotifyDao {

    /**
     * 生成通知
     */

    void createNotify(@Param("notify_person") String notify_person,@Param("send_notify_person")  String send_notify_person,@Param("content")  String content,
                      @Param("article_id")int article_id);
}
