package com.ssh.dao.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.common.util.UuidUtil;
import com.ssh.dao.PersonDao;
import com.ssh.domain.Person;

@RunWith(SpringJUnit4ClassRunner.class)// 指定测试用例的运行器 这里是指定了Junit4
@ContextConfiguration(locations = { "/spring/applicationContext-jdbc.xml" })
public class TestPersonDaoImpl {

	@Resource(name = "personDaoImpl")
	private PersonDao personDao;

	@Test
//	@Transactional
	// 使用该注释会使用事务，而且在测试完成之后会回滚事务，也就是说在该方法中做出的一切操作都不会对数据库中的数据产生任何影响
	// @Rollback(false) //这里设置为false，就让事务不回滚
	public void testAdd() {
		Person person = new Person();
		person.setId(UuidUtil.getUuid());
		person.setName("zjl");
		person.setBirth(new Date());
		person.setAge(12);
		this.personDao.addPerson(person);
	}

}
