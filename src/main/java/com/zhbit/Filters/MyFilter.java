package com.zhbit.Filters;

import com.zhbit.entity.ChildComment;
import com.zhbit.entity.FatherComment;
import com.zhbit.entity.Login;
import com.zhbit.entity.UserMessage;
import com.zhbit.enums.CurrencyEnum;
import com.zhbit.enums.FatherCommentEnum;
import com.zhbit.enums.LoginEnum;
import com.zhbit.util.JsonUtils;
import com.zhbit.util.ReaderUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.*;

public class MyFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器初始化");
    }


    /**
     * 主要用于提交数据的格式的验证,如判空,长度限制之类的.
     *
     * @param req
     * @param resp
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        //由于
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        //流重写,因为req只能读一次,重写成可以读多次的
        HttpServletRequest request = new BodyReaderHttpServletRequestWrapper(httpServletRequest);
        //从request中读取json数据
        String result = ReaderUtils.ReaderJsonString(request);
        //获取请求的相对地址 如http://localhost:8080/Login/doLogin   得到/Login/doLogin
        String URI = httpServletRequest.getRequestURI();
        //若json为空串 直接返回信息
        if (result.trim().equals("") || result == null){
            JsonUtils.CreateJsonAndSend(response, CurrencyEnum.JSONISNULL.toMap());
            return;
    }
        if (URI.equals("/Login/doLogin")) {
                //将得到json字符串转为bean
                Login loginTmp = (Login) JsonUtils.JsonStringToBean(result, Login.class);
                String email = loginTmp.getEmail();
                String pwd = loginTmp.getPwd();
                if (email != null && pwd != null && !email.trim().equals("") && !pwd.trim().equals("")) {
                filterChain.doFilter(request, resp);//通过验证放行
            }
        } else if (URI.equals("/register/getUser")) {
            Login loginTmp = (Login) JsonUtils.JsonStringToBean(result, Login.class);
            String email = loginTmp.getEmail();
            String pwd = loginTmp.getPwd();
            if (email != null && pwd != null && !email.trim().equals("") && !pwd.trim().equals("")) {
                //TODO 邮箱验证 和密码长度验证
                filterChain.doFilter(request, resp);
            }else {
                JsonUtils.CreateJsonAndSend(response,LoginEnum.ISNULL.toMap());
            }
        }
        //关于评论的验证
        else if (URI.equals("/comments/commitFather")) {
           FatherComment fatherComment =(FatherComment) JsonUtils.JsonStringToBean(result, FatherComment.class);
            String content = fatherComment.getContent();
            //评论模块没有意识使用设计模式 抽象一个基类出来- -坑爹！代码写多了0.0 凑合着用吧
            if(content==null||content.trim().equals("")){
                //没有评论
                JsonUtils.CreateJsonAndSend(response, FatherCommentEnum.CONTENTNULL.toMap());
                //评论字数超限制
                if(content.length()>300){
                    JsonUtils.CreateJsonAndSend(response,FatherCommentEnum.CONTENTLENGTH.toMap());
                }
            }else {
                filterChain.doFilter(request, resp);
            }

        } else if (URI.equals("/comments/commitChild")) {
           ChildComment childComment =(ChildComment) JsonUtils.JsonStringToBean(result, ChildComment.class);
            String content = childComment.getContent();
            if(content==null||content.trim().equals("")){
                //没有评论
                JsonUtils.CreateJsonAndSend(response, FatherCommentEnum.CONTENTNULL.toMap());
                //评论字数超限制
                if(content.length()>300){
                    JsonUtils.CreateJsonAndSend(response,FatherCommentEnum.CONTENTLENGTH.toMap());
                }
            }else{
                filterChain.doFilter(request, resp);
            }
        }
        //TODO 提交的文章验证和其他


    }

    public void destroy() {
        System.out.println("过滤器销毁");
    }
}
