package com.zhbit.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.zhbit.dao.ArticleDao;
import com.zhbit.dao.TagDao;
import com.zhbit.dto.PublishArticle;
import com.zhbit.entity.Article;
import com.zhbit.entity.Tag;
import com.zhbit.service.interfaces.ArticleService;
import com.zhbit.service.interfaces.TagService;
import com.zhbit.service.interfaces.UploadService;
import com.zhbit.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * 编写博客界面的操作
 */
@Service
public class UploadServiceImpl implements UploadService {
	
	@Autowired
	private ArticleService ad;


	@Autowired
	private TagService tagService;



	private static final String BASEURL="http://localhost:8081/source/";
	
	public String uploadImg(String user,MultipartFile file,String writeToUrl) {
		String fileName=file.getOriginalFilename();
		fileName=""+user+new Date().getTime()+fileName.substring(fileName.lastIndexOf('.'));
		String fullPath=writeToUrl+fileName;
		File f=new File(fullPath);
		try {
			file.transferTo(f);
		} catch (IllegalStateException e) {
		} catch (IOException e) {
			e.printStackTrace();
		}
		return JsonUtils.getImgUrl(true, BASEURL+"image/"+fileName, null);
	}

	@Transactional
	public boolean publish(PublishArticle publishArticle, String writeToUrl) {
		// TODO  如果是编辑 要判断这篇文章是否属于目前登录的人 不是不予访问
		//如果没标签 默认设置为其他
		if(publishArticle.getTagList().size()==0){
			publishArticle.getTagList().add("其他");
		}
		String url;
		String FindUrl;
		long time = new Date().getTime();;
		if(publishArticle.getEdid()!=-1&&publishArticle.getEdid()!=0) {
			url=ad.getUrlByArticleId(publishArticle.getEdid());
			FindUrl=url;
			url=url.replaceAll("http://localhost:8081","/usr");
		}else {
			url = writeToUrl + publishArticle.getOwn_id() + time + ".md";  //插入的url
			FindUrl="http://localhost:8081/source/markdown/"+publishArticle.getOwn_id()+ time +".md";  //访问的url

		}
		File f=new File(url);
		try {
			FileOutputStream fos=new FileOutputStream(f);
			PrintWriter pw=new PrintWriter(fos);
			pw.print(publishArticle.getContent());
			pw.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//封装文章
		Article article=new Article();
		article.setUrl(FindUrl);
		article.setTitle(publishArticle.getTitle());
		article.setOwn_id(publishArticle.getOwn_id());
		//截取文章前100个字作为简介
		article.setArticle_profile(publishArticle.getContent().substring(0,100));
		int article_id;
		if(publishArticle.getEdid()!=-1&&publishArticle.getEdid()!=0){	//编辑旧文章
			tagService.deleteAllByArticleId(publishArticle.getEdid());
			ad.updateTitleAndProfile(article.getTitle(),article.getArticle_profile(),publishArticle.getEdid());
			article_id=publishArticle.getEdid();
		}else {
			ad.insertArticle(article); //插入文章到数据库
			//对于标签的插入
			article_id = article.getArticle_id();
		}

		Tag[] allTags = tagService.getAllTags(); //先取全部标签
		for (String s:publishArticle.getTagList()){
			for (Tag tag:allTags){
				if(s.equals(tag.getTag_name())){
					tagService.insertAritcleTag(article_id,tag.getTag_id());
				}
			}
		}

		return true;
	}
	
	public String edit(int id) {
		//去数据库查编辑文章是否属于登录的用户


		Article article=ad.getArticleByArticleId(id);
		String url=article.getUrl().replaceAll("http://localhost:8081","/usr");
		File f=new File(url);
		FileInputStream fis;
		try {
			fis = new FileInputStream(f);
			BufferedReader bf=new BufferedReader(new InputStreamReader(fis));
			StringBuilder main=new StringBuilder();
			String temp;
			while((temp=bf.readLine())!=null){
				main.append(temp);
				main.append("\n");
			}
			bf.close();
			fis.close();
			return JsonUtils.getArticleMD(true,article.getTitle(), main.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
