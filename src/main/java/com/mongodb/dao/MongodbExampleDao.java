package com.mongodb.dao;

import com.mongodb.domain.Person;

/**
 * mongodbDao操作
 * 
 * @author zjl
 * 
 */
public interface MongodbExampleDao {

	void add(Person person);
	
	void update(Person person);
	
	void remove(Person person);
	
	Person findById(String id);
}
