package com.zhbit.util;

import com.zhbit.entity.Login;
import com.zhbit.enums.LoginEnum;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class JsonUtils {

    /**
     * 将json字符串转为对象
     * @return
     */
    public static Object JsonStringToBean(String json,Class cls){
            JSONObject jsonObject = JSONObject.fromObject(json);
            Object o = JSONObject.toBean(jsonObject, cls);
            return o;
    }

    public static String BeanToJsonString(Object o){
        JSONObject jsonObject = JSONObject.fromObject(o);
        return jsonObject.toString();
    }

    /**
     * 封装json数据 准备发往前台
     */
    public static void CreateJsonAndSend(HttpServletResponse response,Map<String,String> map) throws IOException {
        //解决跨域问题
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "accept,content-type");
        response.setHeader("Access-Control-Allow-Methods", "OPTIONS,GET,POST,DELETE,PUT");
        //设置编码格式
        response.setContentType("application/json; charset=utf-8");
        String userJson = BeanToJsonString(map);
        OutputStream out = response.getOutputStream();
        out.write(userJson.getBytes("UTF-8"));
        out.flush();
    }

}
