/**
 * 
 */
package com.ssh.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.common.domain.Paging;
import com.ssh.dao.PersonDao;
import com.ssh.domain.Person;
import com.ssh.service.PersonService;

/**
 * @Description: 人员操作service实现
 * @author zjl
 * @date 2014年10月29日 下午4:27:37
 */
@Service
@Transactional(isolation = Isolation.READ_UNCOMMITTED)
public class PersonManyHibernateServiceImpl implements PersonService {

	@Resource(name = "personDaoHibernateImpl1")
	private PersonDao personDao1;
	
	
	@Resource(name = "personDaoHibernateImpl")
	private PersonDao personDao;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ssh.service.PersonService#addPerson(com.ssh.domain.Person)
	 */
	@Override
	public boolean addPerson(Person person) {
		Person person1=new Person();
		person1.setAge(person.getAge());
		person1.setName(person.getName());
		person1.setBirth(person.getBirth());
		this.personDao.addPerson(person);
		
		this.personDao1.addPerson(person1);

		
//		String[] strArr=new String[1];
//		strArr[1]="test";
	
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ssh.service.PersonService#searchPersons(com.common.domain.Paging)
	 */
	@Override
	public Paging<Person> searchPersons(Paging<Person> paging) {
		Paging<Person> persons = this.personDao.searchPersons(paging);
		return persons;
	}

}
