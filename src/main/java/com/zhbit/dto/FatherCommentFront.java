package com.zhbit.dto;

import com.zhbit.entity.ChildComment;
import com.zhbit.entity.FatherComment;

import java.util.Date;
import java.util.*;

/**
 * 父评论所需的信息  应该使用dao中的FatherComment做一个组合的   写好了就懒得改了
 */
public class FatherCommentFront {
    private int article_id;
    private int comment_id;
    private String user_name;
    private String response_user;
    private Date create_time;
    private String head_portrait;
    private int click_like;
    private int click_unlike;
    private String content;
    //父评论数
    private int FatherCount;  //用于分页
    //评论总数
    private int CommentCount;  //用于显示评论总数

    //鉴于博客评论不会太多,一次全查出来算了
    //这条父评论下所有的子评论
    List<ChildComment> childList;

    public String getHead_portrait() {
        return head_portrait;
    }

    public void setHead_portrait(String imgUrl) {
        this.head_portrait = imgUrl;
    }


    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getResponse_user() {
        return response_user;
    }

    public void setResponse_user(String response_user) {
        this.response_user = response_user;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public int getClick_like() {
        return click_like;
    }

    public void setClick_like(int click_like) {
        this.click_like = click_like;
    }

    public int getClick_unlike() {
        return click_unlike;
    }

    public void setClick_unlike(int click_unlike) {
        this.click_unlike = click_unlike;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @Override
    public String toString() {
        return "FatherCommentFront{" +
                "article_id='" + article_id + '\'' +
                ", user_name='" + user_name + '\'' +
                ", response_user='" + response_user + '\'' +
                ", create_time=" + create_time +
                ", head_portrait='" + head_portrait + '\'' +
                ", click_like=" + click_like +
                ", click_unlike=" + click_unlike +
                ", content='" + content + '\'' +
                ", CommentCount='" + CommentCount + '\'' +
                '}';
    }

    public int getCommentCount() {
        return CommentCount;
    }

    public void setCommentCount(int commentCount) {
        CommentCount = commentCount;
    }


    public int getFatherCount() {
        return FatherCount;
    }

    public void setFatherCount(int fatherCount) {
        FatherCount = fatherCount;
    }




    public void setChildList(List<ChildComment> childList) {
        this.childList = childList;
    }

    public List<ChildComment> getChildList() {
        return childList;
    }


}
