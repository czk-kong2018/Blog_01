package com.zhbit.service.impl;

import com.zhbit.dao.TagDao;
import com.zhbit.entity.Tag;
import com.zhbit.service.interfaces.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagDao tagDao;
    public Tag[] getAllTags() {
        Tag[] allTags = tagDao.getAllTags();
        return allTags;
    }

    public Tag[] getUserTags(int user_id) {
        Tag[] userTags = tagDao.getUserTags(user_id);
        return userTags;
    }

    public void insertAritcleTag(int article_id,int tag_id) {
        tagDao.insertArticleTag(article_id,tag_id);
    }

    public void deleteAllByArticleId(int article_id) {
        tagDao.deleteAllByArticleId(article_id);
    }
}
