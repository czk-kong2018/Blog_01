package com.zhbit.controller;


import com.zhbit.dto.CommentList;
import com.zhbit.dto.FatherCommentFront;
import com.zhbit.entity.ChildComment;
import com.zhbit.enums.CurrencyEnum;
import com.zhbit.service.interfaces.CommonService;
import com.zhbit.service.interfaces.UserMessageService;
import com.zhbit.util.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        System.out.println("commentTest");
        List<FatherCommentFront> fatherCommentByPage = commonService.getFatherCommentByPage(article_id, Integer.parseInt(current_page), Integer.parseInt(OnePageCount));
        return  fatherCommentByPage;
    }



    @RequestMapping(value = "/commitFather",method = RequestMethod.POST)
    public void commitComment(@RequestBody FatherCommentFront commitComm, HttpServletResponse response) throws IOException {
        System.out.println(commitComm);
        int res = commonService.commitCommentAndCreateNotify(commitComm);
        JsonUtils.CreateJsonAndSend(response,CurrencyEnum.COMMITSUCCESS.toMap());//提交成功
    }



    @RequestMapping(value = "/commitChild",method = RequestMethod.POST)
    public void commitComment(@RequestBody ChildComment childComment,HttpServletResponse response) throws IOException {
        System.out.println(childComment);
        int res = commonService.commitCommentAndCreateNotifyChild(childComment);
        JsonUtils.CreateJsonAndSend(response,CurrencyEnum.COMMITSUCCESS.toMap());  //提交成功
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
