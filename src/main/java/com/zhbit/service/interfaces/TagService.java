package com.zhbit.service.interfaces;

import com.zhbit.entity.Tag;

public interface TagService {

    Tag[] getAllTags();

    Tag[] getUserTags(int user_id);
}
