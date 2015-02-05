package com.rpc.hessian.client.domain;

import java.io.Serializable;

/**
 * 
 * @Description: 新闻实体
 * @author zjl
 * @date 2015年2月4日 上午11:44:19
 */
public class News implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	
	private String title;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
