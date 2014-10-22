package com.search.bean;

import org.apache.solr.client.solrj.beans.Field;


/**
 * 
 * 事件，图片，音乐，视频
 * 
 * @author wzh
 * 
 */
public class NewsIndex {
	@Field
	private String title;//搜索的字段
	@Field
	private String id; // id
	
	@Field
	private String content;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
 }
