package com.zhbit.dto;

import com.zhbit.entity.ChildComment;
import java.util.*;

/**
 * 可忽略
 *  未使用到,是将父评论和子评论一同封装在一起发送过来 数据结构分层次
 */
public class CommentList {

    public List<HashMap<FatherCommentFront, List<ChildComment>>> getList() {
        return list;
    }

    private List<HashMap<FatherCommentFront,List<ChildComment>>> list;

    public CommentList(List<HashMap<FatherCommentFront, List<ChildComment>>> list) {
        this.list = list;
    }

}
