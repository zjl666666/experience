package com.ssh.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.common.domain.Paging;
import com.common.util.UuidUtil;
import com.ssh.domain.Person;
import com.ssh.service.PersonService;

//指定测试用例的运行器 这里是指定了Junit4
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring/applicationContext-hibernate.xml" })
public class TestPersonManyHibernateServiceImpl {

	@Resource(name = "personManyHibernateServiceImpl")
	private PersonService personService;

	@Test
	// @Transactional
	// 使用该注释会使用事务，而且在测试完成之后会回滚事务，也就是说在该方法中做出的一切操作都不会对数据库中的数据产生任何影响
	// @Rollback(false) //这里设置为false，就让事务不回滚
	public void testAdd() {
		Person person = new Person();
		person.setName("zjl");
		person.setBirth(new Date());
		person.setAge(12);
		this.personService.addPerson(person);
	}

	@Test
	public void testSearch() {
		Paging<Person> paging = new Paging<Person>();
		paging.setCurrentPageNo(0);
		Paging<Person> result = personService.searchPersons(paging);
		List<Person> persons = result.getResultList();
		for (Person person : persons) {
			System.out.print(person.getName());
		}
	}
}
