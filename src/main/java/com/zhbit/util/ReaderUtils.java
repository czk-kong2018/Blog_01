package com.zhbit.util;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ReaderUtils {
    /**
     * 读取request流中的 ajax所提交过来的json数据
     * @param request
     * @return
     */
    public static String ReaderJsonString(HttpServletRequest request){
        String param= null;
        try {
            BufferedReader streamReader = new BufferedReader( new InputStreamReader(request.getInputStream(), "UTF-8"));
            StringBuilder responseStrBuilder = new StringBuilder();
            String inputStr;
            while ((inputStr = streamReader.readLine()) != null)
                responseStrBuilder.append(inputStr);

            param=responseStrBuilder.toString();
            System.out.println(param);
        } catch (Exception e) {
            e.printStackTrace();
        }
       return param;
    }
}
