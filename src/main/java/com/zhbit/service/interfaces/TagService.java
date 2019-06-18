package com.zhbit.service.interfaces;

import com.zhbit.entity.Tag;

import java.util.List;

public interface TagService {

    Tag[] getAllTags();

    Tag[] getUserTags(int user_id);

    void insertAritcleTag(int article_id, int tag_id);

    void deleteAllByArticleId(int article_id);
}
