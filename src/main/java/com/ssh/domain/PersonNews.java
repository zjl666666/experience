package com.ssh.domain;

import java.util.Date;

/**
 * 
 * @Description: 相关新闻
 * @author zjl
 * @date 2014年12月26日 上午11:00:13
 */
public class PersonNews {

	private String id;

	private Person person;

	private String newsName;

	private Date modifyTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

    
	public String getNewsName() {
		return newsName;
	}

	public void setNewsName(String newsName) {
		this.newsName = newsName;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
}
