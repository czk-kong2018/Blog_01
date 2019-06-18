package com.zhbit.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;

import com.zhbit.service.interfaces.YZMService;
import com.zhbit.util.JsonUtils;
import com.zhbit.util.YZMUtil;
import org.springframework.stereotype.Service;

@Service
public class YZMServiceImpl implements YZMService {

    public boolean checkYZM(String correctYZM,String yzm,long yzmBornTime){
        if(correctYZM==null||yzm==null)
            return false;
        long nowTime=new Date().getTime();
        if(nowTime-yzmBornTime>30000){	//30s lifetime
            System.out.println("time out: "+(nowTime-yzmBornTime));
            return false;
        }
        if(!YZMUtil.equals(correctYZM,yzm)){
            System.out.println("not equal, correct: "+correctYZM);
            return false;
        }
        return true;
    }

    public String remoteResponseYZM(String correctYZM,String yzm,long yzmBornTime){
        return JsonUtils.remoteResponse(checkYZM(correctYZM,yzm,yzmBornTime));
    }


    public String ranYZM(){
        return YZMUtil.ranYZM();
    }

    public byte[] getYZMImg(String yzm) throws IOException{
        BufferedImage img=YZMUtil.getYZMImg(yzm);
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        ImageIO.write(img, "png", baos);
        byte b[]=baos.toByteArray();
        baos.close();
        return b;
    }

}