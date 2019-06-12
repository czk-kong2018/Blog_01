package com.zhbit.controller;

import com.zhbit.entity.Login;
import com.zhbit.entity.UserMessage;
import com.zhbit.enums.LoginEnum;
import com.zhbit.service.interfaces.LoginService;
import com.zhbit.service.interfaces.UserMessageService;
import com.zhbit.util.JsonUtils;
import com.zhbit.util.UuidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/Login")
@CrossOrigin(origins="*")
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private UserMessageService userMessageService;
    /**
     * 登录
     * @param loginUser
     */
    @ResponseBody
    @RequestMapping(value = "/doLogin",method = RequestMethod.POST)
    public void Login(@RequestBody Login loginUser, HttpServletResponse response, HttpSession session) throws IOException {
        Login login = loginService.getLoginByEmail(loginUser.getEmail());
        if(login!=null) {
            if (loginUser.getPwd().equals(login.getPwd())) {
                UserMessage user = userMessageService.getUserMessageByUserId(login.getLogin_id());
                //TODO 设置cookie
                Cookie cookie1=new Cookie("user_name",user.getUser_name());
                Cookie cookie2=new Cookie("user_id",String.valueOf(user.getUser_id()));
                cookie1.setPath("/");
                cookie2.setPath("/");
                cookie1.setMaxAge(30*60);  //单位是秒
                cookie2.setMaxAge(30*60);
                response.addCookie(cookie1);
                response.addCookie(cookie2);
                //设置session的生命周期在web.xml中  单位是分钟
                session.setAttribute("user",user);

                JsonUtils.CreateJsonAndSend(response,LoginEnum.SUCCESS.toMap());
            } else {
                JsonUtils.CreateJsonAndSend(response,LoginEnum.PWDERROR.toMap());
            }
        }else{

            JsonUtils.CreateJsonAndSend(response, LoginEnum.EMAILNOTEXIST.toMap());
        }


    }
    //注销
    public void LogOut(){

    }


}
