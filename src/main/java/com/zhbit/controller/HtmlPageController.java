package com.zhbit.controller;

import com.zhbit.entity.UserMessage;
import com.zhbit.service.interfaces.UserMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * 返回各种前后台界面
 */
@Controller
@CrossOrigin(origins="*")
public class HtmlPageController {

    @Autowired
    private UserMessageService userMessageService;

    /**
     * 返回用户主界面
     * @param user_name
     * @return
     */
    @RequestMapping("/{user_name}")
    public String getUserBlog(@PathVariable("user_name") String user_name){
        UserMessage user = userMessageService.getUserMessageByUserName(user_name);
        return user!=null? "front/userBlog" : "front/404";
    }

    /**
     * TODO 返回后台管理界面
     * @return
     */
    @RequestMapping("/back/Manager")
    public String getBackManger(HttpServletRequest request, HttpSession httpSession) {
        return "back/manage";
    }
        /**
         * 返回注册界面
         */
        @RequestMapping("/front/Register")
        public String getRegister () {
            return "front/register";
        }
        /**
         * 返回编写博客界面, js太乱暂时不用
         */
        @RequestMapping("/back/Editor")
        public String getWriteBlog () {
            return "writeBlog/editor";
        }

        @RequestMapping("/page/error")
        public String returnError(){
            return "front/404";
        }
    }
