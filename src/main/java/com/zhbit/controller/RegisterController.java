package com.zhbit.controller;


import com.sun.deploy.net.HttpResponse;
import com.zhbit.entity.Login;
import com.zhbit.enums.RegisterEnum;
import com.zhbit.service.interfaces.RegisterService;
import com.zhbit.util.JsonUtils;
import com.zhbit.util.UuidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;



@Controller
@RequestMapping("/register")
@CrossOrigin(origins="*")
public class RegisterController {


    @Autowired
    private RegisterService registerService;
    @RequestMapping("/getUser")
    public void registerUser( @RequestBody Login login,HttpSession httpSession,HttpServletResponse response) throws IOException {
        String email=login.getEmail();
        String pwd=login.getPwd();
            Integer result = registerService.getUserIdByEmail(email);
            if (result == null) {
                String code = UuidUtils.createUUid();
                Login register = registerService.Register(pwd, email, code);

                httpSession.setAttribute(code, register);

                //设置三分钟失效
                httpSession.setMaxInactiveInterval(5 * 60);

                JsonUtils.CreateJsonAndSend(response, RegisterEnum.SENDSUCCESS.toMap()); //邮箱发送成功
            }else {
                JsonUtils.CreateJsonAndSend(response, RegisterEnum.EMAILEXIST.toMap()); //邮箱已存在
            }
    }


    @RequestMapping("/{code}")
    public void registerCheck(@PathVariable("code") String code,HttpServletResponse response,HttpSession session) throws IOException {
        response.setHeader("Content-Type","text/html;charset=UTF-8");
        Login login =(Login)session.getAttribute(code);
        if(login!=null) { {
                session.removeAttribute(code);
            String user_name= registerService.insertUser(login);
            JsonUtils.CreateJsonAndSend(response,RegisterEnum.REGISTERSUCCESS.toMap()); //注册成功

            }
        }else{
            JsonUtils.CreateJsonAndSend(response,RegisterEnum.URLTIMEOUT.toMap()); //链接失效
        }

    }


}
