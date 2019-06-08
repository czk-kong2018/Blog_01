package com.zhbit.controller;


import com.zhbit.entity.Login;
import com.zhbit.service.interfaces.RegisterService;
import com.zhbit.util.UuidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/register")
@CrossOrigin(origins = "*")
public class RegisterController {
    @Autowired
    private RegisterService registerService;
    @RequestMapping("/getUser")
    @ResponseBody
    public boolean registerUser(HttpSession httpSession) throws Exception {
        String email="1493773835@qq.com";
        Integer result = registerService.getUserIdByEmail(email);
        if(result==null) {
            String code= UuidUtils.createUUid();
            String loginName="czk";
            String pwd="123";
            Login register = registerService.Register(loginName, pwd, email, code);
            httpSession.setAttribute(code,register);
            //1分钟失效
            httpSession.setMaxInactiveInterval(60*5);
            return true;
        }
        return false;
    }

    @RequestMapping("/{code}")
    public void registerCheck(@PathVariable("code") String code,HttpSession httpSession){
        Login login =(Login) httpSession.getAttribute(code);
        if(login!=null) {
            httpSession.removeAttribute(code);
            System.out.println(login);
            registerService.insertUser(login);
        }else{
            System.out.println("链接已失效,请重新注册");
        }

    }


}
