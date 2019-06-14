package com.zhbit.service.interfaces;

import com.zhbit.entity.Tag;

import java.util.List;

public interface TagService {

    Tag[] getAllTags();

    Tag[] getUserTags(int user_id);

    public void insertTag(List<Tag> tags, int article_id);
}
