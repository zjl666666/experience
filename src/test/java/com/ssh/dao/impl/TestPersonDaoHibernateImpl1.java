package com.ssh.dao.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.common.util.UuidUtil;
import com.ssh.dao.PersonDao;
import com.ssh.domain.Person;

@RunWith(SpringJUnit4ClassRunner.class)
// 指定测试用例的运行器 这里是指定了Junit4
@ContextConfiguration(locations = { "/spring/applicationContext-hibernate.xml" })
public class TestPersonDaoHibernateImpl1 {
	/**
	 * 记录日志
	 */
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name = "personDaoHibernateImpl1")
	private PersonDao personDao;

	@Test
	// 使用该注释会使用事务，而且在测试完成之后会回滚事务，也就是说在该方法中做出的一切操作都不会对数据库中的数据产生任何影响
	@Transactional
	// 这里设置为false，就让事务不回滚
	@Rollback(false)
	public void testAdd() {
		Person person = new Person();
		person.setName("zjl");
		person.setBirth(new Date());
		person.setAge(12);	
		this.personDao.addPerson(person);
		
	    person=this.personDao.findById(person.getId());
		logger.debug("idLong====="+person.getIdLong());
		logger.debug("id====="+person.getId());
//		logger.debug("modifyTime====="+person.getModifyTime());

	}

}
