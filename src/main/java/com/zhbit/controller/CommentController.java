package com.zhbit.controller;


import com.zhbit.dto.CommentList;
import com.zhbit.dto.FatherCommentFront;
import com.zhbit.entity.ChildComment;
import com.zhbit.service.interfaces.CommonService;
import com.zhbit.service.interfaces.UserMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller

@RequestMapping("/comments")
@CrossOrigin(origins="*")
public class CommentController {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CommonService commonService;
    @Autowired
    private UserMessageService userMessageService;



    /**
     * 分页获取评论
     * @param
     */
    @RequestMapping(value = "/{article_id}/getByPage",method = RequestMethod.POST)
    @ResponseBody
    public List<FatherCommentFront> getCommentByPage(@PathVariable("article_id")int article_id
    , String current_page, String OnePageCount){
        List<FatherCommentFront> fatherCommentByPage = commonService.getFatherCommentByPage(article_id, Integer.parseInt(current_page), Integer.parseInt(OnePageCount));
        return  fatherCommentByPage;
    }



    @RequestMapping(value = "/commitFather",method = RequestMethod.POST)
    @ResponseBody
    public void commitComment(@RequestBody FatherCommentFront commitComm){
        System.out.println(commitComm);
        int res = commonService.commitCommentAndCreateNotify(commitComm);

    }



    @RequestMapping(value = "/commitChild",method = RequestMethod.POST)
    @ResponseBody
    public void commitComment(@RequestBody ChildComment childComment){
        System.out.println(childComment);
        int res = commonService.commitCommentAndCreateNotifyChild(childComment);

    }

    /**
     *  测试使用  可忽略
     * @return
     */
    @RequestMapping(value = "/test")
    @ResponseBody
    public CommentList test(){
     return  commonService.test(6,1,10);

    }


}
