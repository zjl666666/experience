package com.ssh.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.common.util.UuidUtil;
import com.ssh.domain.Person;
import com.ssh.service.PersonMybatisService;

//指定测试用例的运行器 这里是指定了Junit4
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring/applicationContext-mybatis.xml" })
public class TestPersonMybatisServiceImpl {
	/**
	 * 记录日志
	 */
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource(name = "personMybatisServiceImpl")
	private PersonMybatisService personService;

	@Test
	// @Transactional
	// 使用该注释会使用事务，而且在测试完成之后会回滚事务，也就是说在该方法中做出的一切操作都不会对数据库中的数据产生任何影响
	// @Rollback(false) //这里设置为false，就让事务不回滚
	public void testAdd() {
		Person person = new Person();
		person.setId(UuidUtil.getUuid());
		person.setName("zjl");
		person.setBirth(new Date());
		person.setAge(12);
		this.personService.addPerson(person);
	}

	@Test
	public void testSearchPersons() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "zjl");
		List<Person> persons = personService.searchPersons(map);
		for (Person person : persons) {
			logger.debug("person name=====================" + person.getName());
		}
	}

}
