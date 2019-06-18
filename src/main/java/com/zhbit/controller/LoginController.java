package com.zhbit.controller;

import com.zhbit.entity.Login;
import com.zhbit.entity.UserMessage;
import com.zhbit.enums.LoginEnum;
import com.zhbit.service.interfaces.LoginService;
import com.zhbit.service.interfaces.UserMessageService;
import com.zhbit.service.interfaces.YZMService;
import com.zhbit.util.JsonUtils;
import com.zhbit.util.UuidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

@Controller
@RequestMapping("/Login")
@CrossOrigin(origins = "*")
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private UserMessageService userMessageService;
    @Resource
    private YZMService yzmService;

    /**
     * 登录
     *
     * @param loginUser
     */
    @ResponseBody
    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public void Login(@RequestBody Login loginUser, HttpServletResponse response, HttpSession session) throws IOException {
        //先检查验证码
        String correctYZM = session.getAttribute("yzm").toString();
        long t = Long.parseLong(session.getAttribute("t1").toString());
        if (!yzmService.checkYZM(correctYZM, loginUser.getYzm(), t)) {

            JsonUtils.CreateJsonAndSend(response, LoginEnum.YZMERROR.toMap());
            return;
        }
        //先去数据库查邮箱是否存在 存在返回提示信息
        Login login = loginService.getLoginByEmail(loginUser.getEmail());

        if (login != null) {
             if (loginUser.getPwd().equals(login.getPwd())) {
                UserMessage user = userMessageService.getUserMessageByUserId(login.getLogin_id());
                //TODO 设置cookie
                Cookie cookie1 = new Cookie("user_name", user.getUser_name());
                Cookie cookie2 = new Cookie("user_id", String.valueOf(user.getUser_id()));
                cookie1.setPath("/");
                cookie2.setPath("/");
                cookie1.setMaxAge(30 * 60);  //单位是秒    设置生命周期这里是30分钟
                cookie2.setMaxAge(30 * 60);
                response.addCookie(cookie1);
                response.addCookie(cookie2);
                //设置session的生命周期在web.xml中  单位是分钟 也是30分钟
                session.setAttribute("user", user);  //将登录的用户 存到session中 用于权限验证

                JsonUtils.CreateJsonAndSend(response, LoginEnum.SUCCESS.toMap()); //返回登录成功提示
            } else {
                JsonUtils.CreateJsonAndSend(response, LoginEnum.PWDERROR.toMap()); //返回密码错误提示
            }
        } else {

            JsonUtils.CreateJsonAndSend(response, LoginEnum.EMAILNOTEXIST.toMap()); //返回邮箱不存在提示
        }


    }

    //yzm
    @RequestMapping("/yzmcheck.do")    //response bootstrapValidator "remote"
    @ResponseBody
    public String yzmcheck(@RequestParam(value = "yzm", required = false) String yzm, HttpSession session) {
        String correctYZM = (String) session.getAttribute("yzm");
        long t = Long.parseLong(session.getAttribute("t1").toString());
        return yzmService.remoteResponseYZM(correctYZM, yzm, t);
    }

    @RequestMapping("/yzm.do")    //spawn yzm
    public void yzm(HttpSession session, HttpServletResponse response) {
        String yzm = yzmService.ranYZM();
        session.setAttribute("yzm", yzm.toLowerCase());
        session.setAttribute("t1", new Date().getTime());
        try {
            byte b[] = yzmService.getYZMImg(yzm);
            OutputStream os = response.getOutputStream();
            os.write(b);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
