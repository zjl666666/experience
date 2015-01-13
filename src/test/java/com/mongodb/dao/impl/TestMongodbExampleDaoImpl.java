package com.mongodb.dao.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.common.util.UuidUtil;
import com.mongodb.dao.MongodbExampleDao;
import com.mongodb.domain.Person;
import com.search.service.impl.TestIndexServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)// 指定测试用例的运行器 这里是指定了Junit4
@ContextConfiguration(locations = { "/spring/applicationContext-mongodb.xml" })
public class TestMongodbExampleDaoImpl {
	
	private static final Logger logger = LoggerFactory.getLogger(TestMongodbExampleDaoImpl.class);

	@Resource(name = "mongodbExampleDaoImpl")
	private MongodbExampleDao mongodbExampleDao;

	@Test
//	@Transactional
	// 使用该注释会使用事务，而且在测试完成之后会回滚事务，也就是说在该方法中做出的一切操作都不会对数据库中的数据产生任何影响
	// @Rollback(false) //这里设置为false，就让事务不回滚
	public void testAdd() {
		Person person = new Person();
		person.setName("zjl");
		person.setAge(12);
		this.mongodbExampleDao.add(person);
	}

	@Test
	public void testRemove() {
		Person person = new Person();
		person.setId("54b4dfac7f8da34be500abba");
		person.setName("zjl");
		person.setAge(12);
		this.mongodbExampleDao.remove(person);
	}
	
	@Test
	public void testUpdate() {
		Person person = new Person();
		person.setId("54b4dfd87f8db9b442240be8");
		person.setName("modifyzjl");
		person.setAge(15);
		this.mongodbExampleDao.update(person);
	}
	
	
	@Test
	public void testFindById() {
		String id="54b4dfd87f8db9b442240be8";
		Person person=this.mongodbExampleDao.findById(id);
		logger.debug("person name=="+person.getName());
	}
	

}
