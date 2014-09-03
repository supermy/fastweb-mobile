package com.supermy.mongodb.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "news")
@TypeAlias("News")
public class News implements Serializable{

	@Id
	private String id;
	
	private String title;
	private String content;
	private Date newdate;
	private String author;
	
	
	
	public News() {
		super();
	}
	public News(String title, String content, String author) {
		super();
		this.title = title;
		this.content = content;
		this.author = author;
		this.newdate = new Date();
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getNewdate() {
		return newdate;
	}
	public void setNewdate(Date newdate) {
		this.newdate = newdate;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	
//	@Field("m")
//	private Map<String,Object> map = new HashMap<String,Object>();
//
//	public News(String key,Object value) {
//		map.put(key, value);
//	}
//
//	public Map<String,Object> getMap() {
//		return map;
//	}
//
//	public void setMap(Map<String,Object> map) {
//		this.map = map;
//	}
	
}