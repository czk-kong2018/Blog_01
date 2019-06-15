package com.zhbit.controller;

import com.zhbit.entity.Tag;
import com.zhbit.service.interfaces.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller

@RequestMapping("/tags")
@CrossOrigin(origins="*")
public class TagController {

    @Autowired
    private TagService tagService;

    @ResponseBody
    @RequestMapping("/getAll")
    public Tag[] getAllTags(){
        Tag[] allTags = tagService.getAllTags();
        return allTags;
    }


    /**
     * 获取用户所有标签
     * @param user_id
     * @return
     */
    @ResponseBody
    @RequestMapping("/{user_id}/get")
    public Tag[] getUserTags(@PathVariable("user_id") String user_id){
        Tag[] userTags = tagService.getUserTags(Integer.parseInt(user_id));
        return userTags;
    }


}
