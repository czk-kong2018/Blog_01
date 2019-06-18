package com.zhbit.service.interfaces;

import com.zhbit.dto.IndexBlogger;
import com.zhbit.entity.UserMessage;
import org.apache.ibatis.annotations.Param;
import java.util.*;
public interface UserMessageService {

    int getUserId(String user_name);

    UserMessage getUserMessageByUserName(String user_name);

    UserMessage getUserMessageByUserId(int user_id);

    /**
     * @Author  拔锋
     * @return
     */
    List<IndexBlogger> getIndexBlogger();
}
