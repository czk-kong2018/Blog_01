package com.zhbit.interceptors;

import com.zhbit.entity.Login;
import com.zhbit.entity.UserMessage;
import com.zhbit.enums.CurrencyEnum;
import com.zhbit.enums.LoginEnum;
import com.zhbit.enums.UserEnum;
import com.zhbit.util.JsonUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;

/**
 * 访问管理页面,提交评论等需要登录操作的拦截器
 */
public class UserInterceptor implements HandlerInterceptor {
    /**
     * Controller业务方法执行之前执行
     * @return 返回值决定请求是放行还是拦截
     * false:表示拦截
     * true:表示放行
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserMessage user =(UserMessage) request.getSession().getAttribute("user");
        if(user==null){
            JsonUtils.CreateJsonAndSend(response, UserEnum.SESSIONTIMEOUT.toMap());
            return false;
        }
        //检查cookie是否和session一致  防止伪造cookie
            int user_id=0;
            String user_name=null;
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user_name")) {
                    user_name = cookie.getValue();
                }
                if (cookie.getName().equals("user_id")) {
                    user_id = Integer.valueOf(cookie.getValue());
                }
            }
            if (user_id == user.getUser_id() && user_name.equals(user.getUser_name())) {
                    return true;
            }else{
                JsonUtils.CreateJsonAndSend(response, CurrencyEnum.COOKIDEFALSIFY.toMap()); //cookie可能伪造  无效
            }
        return false;


    }

    /**
     * 此方法在Controller的业务方法执行之后执行
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
    /**
     * 此方法在Controller的业务方法执行结束并且视图解析完成后执行
     */
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
