package com.mongodb.domain;


/**
 * 
 * @Description: 人员模型
 * @author zjl
 * @date 2014年10月29日 上午10:30:22
 */
public class Person {
	
	private String id;
	// 姓名
	private String name;
	// 年龄
	private int age;

	
	
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

	
}
