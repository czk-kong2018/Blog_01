package com.zhbit.service.impl;

import com.zhbit.dao.UserMessageDao;
import com.zhbit.entity.UserMessage;
import com.zhbit.service.interfaces.UserMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMessageServiceImpl  implements UserMessageService {

    @Autowired
    private UserMessageDao userMessageDao;

    public int getUserId(String user_name) {
        int user_id = userMessageDao.getUserId(user_name);
        return user_id;
    }

    public UserMessage getUserMessageByUserName(String user_name) {
        UserMessage userMessageByUserName = userMessageDao.getUserMessageByUserName(user_name);
        return userMessageByUserName;
    }
}
