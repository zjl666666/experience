package com.ssh.domain;

import java.util.Date;

/**
 * 
 * @Description: 人员模型
 * @author zjl
 * @date 2014年10月29日 上午10:30:22
 */
public class Person {
	// id
	private String id;
	// 姓名
	private String name;
	// 年龄
	private int age;
	// 出生日期
	private Date birth;
    
	private Date modifyTime;
	
	private Long idLong;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Long getIdLong() {
		return idLong;
	}

	public void setIdLong(Long idLong) {
		this.idLong = idLong;
	}
	
}
