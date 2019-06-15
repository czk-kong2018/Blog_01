package com.zhbit.service.interfaces;

import com.zhbit.dto.IndexBlogger;
import com.zhbit.entity.UserMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMessageService {

    int getUserId(String user_name);

    UserMessage getUserMessageByUserName(String user_name);

    List<IndexBlogger> getIndexBlogger();
}
