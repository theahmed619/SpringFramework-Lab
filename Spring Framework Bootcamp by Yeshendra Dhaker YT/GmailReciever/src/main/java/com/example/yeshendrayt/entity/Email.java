package com.example.yeshendrayt.entity;

import java.util.Date;

public class Email {
	
	private String from;
	private String subject;
	private Date recivedDate;
	private String content;
	
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Date getRecivedDate() {
		return recivedDate;
	}
	public void setRecivedDate(Date recivedDate) {
		this.recivedDate = recivedDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	

}
