package com.zhbit.service.interfaces;


import com.zhbit.dto.CommentList;
import com.zhbit.dto.FatherCommentFront;
import com.zhbit.entity.ChildComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommonService {


    /**
     * 提交评论父评论
     */
    int commitCommentAndCreateNotify(FatherCommentFront comment);

    /**
     * 提交子评论
     * @param comment
     * @return
     */
    int commitCommentAndCreateNotifyChild(ChildComment comment);

    /**
     * 分页获取父评论
     * @param article_id
     * @param current_page
     * @param OnePageCount
     * @return
     */
    List<FatherCommentFront> getFatherCommentByPage(int article_id, int current_page, int OnePageCount);

    CommentList test(int article_id, int current_page, int OnePageCount);

}
