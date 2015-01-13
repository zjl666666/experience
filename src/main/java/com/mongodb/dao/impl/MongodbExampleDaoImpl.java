/**
 * 
 */
package com.mongodb.dao.impl;

import javax.annotation.Resource;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import static org.springframework.data.mongodb.core.query.Criteria.where;

import com.mongodb.dao.MongodbExampleDao;
import com.mongodb.domain.Person;

/**
 * @Description: TODO
 * @author zjl
 * @date 2015年1月13日 下午4:06:09
 */
@Repository
public class MongodbExampleDaoImpl implements MongodbExampleDao {

	@Resource(name = "mongoTemplate")
	private MongoTemplate mongoTemplate;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mongodb.dao.MongodbExampleDao#add(com.mongodb.domain.Person)
	 */
	@Override
	public void add(Person person) {
		this.mongoTemplate.save(person);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mongodb.dao.MongodbExampleDao#update(com.mongodb.domain.Person)
	 */
	@Override
	public void update(Person person) {
		String id = person.getId();
		if (StringUtils.isBlank(id)) {
			return;
		}
        Query query=new Query();
        query.addCriteria(where("id").is(id));
        Update update=new Update();
        update.set("name", person.getName());
        update.set("age", person.getAge());
		this.mongoTemplate.updateFirst(query, update, Person.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mongodb.dao.MongodbExampleDao#remove(com.mongodb.domain.Person)
	 */
	@Override
	public void remove(Person person) {
		this.mongoTemplate.remove(person);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mongodb.dao.MongodbExampleDao#findById(java.lang.String)
	 */
	@Override
	public Person findById(String id) {
		return this.mongoTemplate.findById(id, Person.class);
	}

}
