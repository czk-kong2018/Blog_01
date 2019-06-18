package com.zhbit.service.interfaces;

import java.io.IOException;

public interface YZMService {

    public boolean checkYZM(String correctYZM,String yzm,long yzmBornTime);
    public String remoteResponseYZM(String correctYZM,String yzm,long yzmBornTime);
    public String ranYZM();
    public byte[] getYZMImg(String yzm) throws IOException;

}