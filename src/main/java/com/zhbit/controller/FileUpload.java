package com.zhbit.controller;

import com.zhbit.util.FileUploadUtils;
import com.zhbit.util.UuidUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.Iterator;
import java.util.UUID;

@Controller

@RequestMapping("/springmvc")
@CrossOrigin(origins="*")
public class FileUpload {

    /*
     *采用spring提供的上传文件的方法
     */
    @ResponseBody
    @RequestMapping("/fileUpload")
    public String springUpload(HttpServletRequest request) throws IllegalStateException, IOException
    {
        String BaseUrl="/usr/source/image/";
        String path = FileUploadUtils.UpLoad(request, BaseUrl);
        return path;
    }
}
