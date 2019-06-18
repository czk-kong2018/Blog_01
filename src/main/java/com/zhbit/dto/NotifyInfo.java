package com.zhbit.dto;

public class NotifyInfo {
	
	private Integer notify_id;
	private String send_notify_person;
	private String content;
	private boolean isread;
	private String articleTitle;
	private String articleURL;
	private int article_id;
	private String user_name; //文章的作者名 后台管理需要用到
	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}






	public int getArticle_id() {
		return article_id;
	}

	public void setArticle_id(int article_id) {
		this.article_id = article_id;
	}


	
	public Integer getNotify_id() {
		return notify_id;
	}
	
	public void setNotify_id(Integer notify_id) {
		this.notify_id = notify_id;
	}
	
	public String getSend_notify_person() {
		return send_notify_person;
	}

	public void setSend_notify_person(String send_notify_person) {
		this.send_notify_person = send_notify_person;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public boolean getIsread() {
		return isread;
	}
	
	public void setIsread(boolean isread) {
		this.isread = isread;
	}

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}
	
	public String getArticleURL() {
		return articleURL;
	}
	
	public void setArticleURL(String articleURL) {
		this.articleURL = articleURL;
	}
	
}
