package com.zhbit.service.impl;

import com.zhbit.dao.RegisterDao;
import com.zhbit.dao.UserMessageDao;
import com.zhbit.entity.Login;
import com.zhbit.service.interfaces.RegisterService;
import com.zhbit.util.MailUtils;
import com.zhbit.util.UuidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private RegisterDao registerDao;
    @Autowired
    private UserMessageDao userMessageDao;
    /**
     * 返回一个 注册Login对象 保存到session中
     * @param pwd
     * @param email
     * @param code
     * @return
     */
    public Login Register( String pwd, String email,String code) {
        String url="http://localhost:8080/register/"+code;
        // 2.4设置邮件内容
        String content = "<html><head><body><h1>这是一封激活邮件,请在3分钟内完成激活,激活请点击以下链接</h1><br>"+url+"<br> <a href='"+url+"'></a>"+"</body></head></html>";
        //保存注册用户信息到session中
        Login login=new Login();
        login.setEmail(email);
        login.setPwd(pwd);
        try {
            MailUtils.sendMail(email,content); //发送邮件
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("发送邮件失败");
        }
        return login;
    }

    /**
     * 返回插入的用户信息的 随机生成的用户名  需要使用到 用于重定向
     * @param login
     * @return
     */
    @Transactional
    public String insertUser(Login login) {
        int i = registerDao.insertUser(login);
        int user_id=registerDao.getUserIdByEmail(login.getEmail());
        //默认用户名生成uuid 随机 取前8位  出现重复的概率堪比中彩票, 中了再说
        String user_name= UuidUtils.createUUid().substring(0,8);
        int i1 = userMessageDao.insertUserMessage(user_id,user_name, "http://localhost:8081/source/image/default.jpg");
        return user_name;
    }

    public Integer getUserIdByEmail(String email) {
        Integer result = registerDao.getUserIdByEmail(email);
        return result;
    }
}
