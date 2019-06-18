package com.zhbit.entity;

public class Notify {
	
	private Integer notify_id;
	private String notify_person;
	private String send_notify_person;
	private String content;
	private boolean isread;
	private Integer article_id;
	
	public Integer getNotify_id() {
		return notify_id;
	}
	
	public void setNotify_id(Integer notify_id) {
		this.notify_id = notify_id;
	}
	
	public String getNotify_person() {
		return notify_person;
	}
	
	public void setNotify_person(String notify_person) {
		this.notify_person = notify_person;
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
	
	public Integer getArticle_id() {
		return article_id;
	}
	
	public void setArticle_id(Integer article_id) {
		this.article_id = article_id;
	}
	
}
