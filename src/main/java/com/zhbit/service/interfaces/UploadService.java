package com.zhbit.service.interfaces;

import com.zhbit.dto.PublishArticle;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
	
	public String uploadImg(String user, MultipartFile file,String writeToUrl);
	public boolean publish(PublishArticle publishArticle, String writeToUrl);
	public String edit(int id);
	
}
