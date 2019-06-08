package com.zhbit.service.impl;

import com.zhbit.dao.*;
import com.zhbit.dto.CommentList;
import com.zhbit.dto.FatherCommentFront;
import com.zhbit.entity.ChildComment;
import com.zhbit.service.interfaces.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private CommentDao CommentDao;
    @Autowired
    private NotifyDao notifyDao;
    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private UserMessageDao userMessageDao;
    
    @Autowired
    private ChildCommentDao childCommentDao;



    /**
     * 父评论  存到数据库中 并生成通知
     * @param comment
     * @return
     */
    @Transactional
    public int commitCommentAndCreateNotify(FatherCommentFront comment) {
        int userId = userMessageDao.getUserId(comment.getUser_name());
        int res = CommentDao.commitComment(comment, userId);   //存储父评论
        String notify_person=comment.getResponse_user();
        String send_notify_person=comment.getUser_name();
        int article_id=comment.getArticle_id();
        if(!notify_person.equals(send_notify_person)) {
            String notifyContent = send_notify_person + "@评论了你:"+comment.getContent();
            //创建评论后　　通知消息
            notifyDao.createNotify(notify_person,send_notify_person,notifyContent,article_id);
        }
        return res;
    }

    /**
     * 子评论 存到数据库中   并生成通知
     * @param comment
     * @return
     */
    @Transactional
    public int commitCommentAndCreateNotifyChild(ChildComment comment){
        int i = childCommentDao.commitComment(comment); //存储子评论
        String notify_person=comment.getResponse_user();
        String send_notify_person=comment.getAuthor_name();
        if(comment.getResponse_user()!=null){   //如果有@某人,创建一个通知
            String notifyContent = send_notify_person + "@评论了你:"+comment.getContent();
            //创建评论后　　通知消息
            notifyDao.createNotify(notify_person,send_notify_person,notifyContent,comment.getArticle_id());
        }
        return i;

    }


    public List<FatherCommentFront> getFatherCommentByPage(int article_id, int current_page, int OnePageCount) {
        //父评论获取
        int from=(current_page-1)*OnePageCount;
        int count=OnePageCount;
        List<FatherCommentFront> fatherCommentFronts = CommentDao.getCommentByPage(article_id, from, count);
        int fatherCount=CommentDao.getFatherCommentCount(article_id);  //根据文章id查询父评论总数

        //查出文章下所有子评论 再分别插到父评论里 不用做太多次数据库查询
        List<ChildComment> child = childCommentDao.getChildCommentByArticleId(article_id);
        List<ChildComment> tmp=new ArrayList<ChildComment>();
        for (FatherCommentFront f:fatherCommentFronts) {
            //设置子评论
            for (ChildComment c:child) {
                if(c.getComment_id()==f.getComment_id()){
                    tmp.add(c);
                }
            }
            f.setChildList(tmp);
            f.setCommentCount(articleDao.getCommentCount(f.getArticle_id()));//设置评论总数 根据Articledao  懒得改了 其实可查子评论总数再加父评论总数就行- - 写都写好了
            f.setFatherCount(fatherCount);  //设置父评论数
            f.setChildList(childCommentDao.getChildCommentByCommentId(f.getComment_id()));//设置父评论下所有子评论

            tmp.clear();//清空tmp

        }

        return fatherCommentFronts;
    }


    /**
     * 忽略  测试新数据结构使用的方法 未使用
     * @param article_id
     * @param current_page
     * @param OnePageCount
     * @return
     */
    public CommentList test(int article_id, int current_page, int OnePageCount){
       CommentList commentList= new CommentList(new ArrayList<HashMap<FatherCommentFront, List<ChildComment>>>());

        List<ChildComment> childComments=new ArrayList<ChildComment>();
        //父评论获取
        int from=(current_page-1)*OnePageCount;
        int count=OnePageCount;
        List<FatherCommentFront> fatherCommentFronts = CommentDao.getCommentByPage(article_id, from, count);
        if(fatherCommentFronts.size()!=0) {
            //根据父评论查询子评论 效率太低
            for (FatherCommentFront f : fatherCommentFronts) {

                List<ChildComment> tmp = childCommentDao.getChildCommentByCommentId(f.getComment_id());
                HashMap<FatherCommentFront, List<ChildComment>> hashmap = new HashMap<FatherCommentFront, List<ChildComment>>();
                hashmap.put(f,tmp);
                commentList.getList().add(hashmap);
            }

        }
        return commentList;
    }

}
