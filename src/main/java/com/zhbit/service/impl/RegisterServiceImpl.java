package com.zhbit.service.impl;

import com.zhbit.dao.RegisterDao;
import com.zhbit.dao.UserMessageDao;
import com.zhbit.entity.Login;
import com.zhbit.service.interfaces.RegisterService;
import com.zhbit.util.MailUtils;
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
     * @param loginName
     * @param pwd
     * @param email
     * @param code
     * @return
     */
    public Login Register(String loginName, String pwd, String email,String code) {
        String url="http://localhost:8080/register/"+code;
        // 2.4设置邮件内容
        String content = "<html><head><body><h1>这是一封激活邮件,请在5分钟内完成激活,激活请点击以下链接</h1><br>"+url+"<br> <a href='"+url+"'></a>"+"</body></head></html>";
        //保存注册用户信息到session中
        Login login=new Login();
        login.setEmail(email);
        login.setLogin_name(loginName);
        login.setPwd(pwd);
        try {
            MailUtils.sendMail(email,content);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("发送邮件失败");
        }
        return login;
    }

    @Transactional
    public int insertUser(Login login) {
        int i = registerDao.insertUser(login);
        int user_id=registerDao.getUserIdByEmail(login.getEmail());
        int i1 = userMessageDao.insertUserMessage(user_id,login.getLogin_name(), "http://localhost:8081/source/image/default.jpg");
        return i1;
    }

    public Integer getUserIdByEmail(String email) {
        Integer result = registerDao.getUserIdByEmail(email);
        return result;
    }
}
