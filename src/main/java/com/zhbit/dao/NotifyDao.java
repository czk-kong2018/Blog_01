package com.zhbit.dao;

import com.zhbit.dto.NotifyInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import java.util.*;
public interface NotifyDao {

    /**
     * 生成通知
     */

    void createNotify(@Param("notify_person") String notify_person,@Param("send_notify_person")  String send_notify_person,@Param("content")  String content,
                      @Param("article_id")int article_id);


    /**
     * 后台通知管理
     * Author
     * @param userName
     * @return
     */
     List<NotifyInfo> notifyInfo(/*int from,int count,*/String userName);
     void delete(int notifyID);
     void hasread(int notifyID);

     //删除全部通知 通过文章id
    @Delete("delete from notify where article_id=#{article_id}")
     void deleteAllByArticleId(@Param("article_id") int article_id);

}
