package com.zhbit.service.interfaces;

import com.zhbit.entity.UserMessage;
import org.apache.ibatis.annotations.Param;

public interface UserMessageService {

    int getUserId(String user_name);

    UserMessage getUserMessageByUserName(String user_name);
}
