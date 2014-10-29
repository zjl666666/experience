/**
 * 
 */
package com.ssh.service.impl;

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
public class PersonServiceImpl implements PersonService {
	@Autowired
	private PersonDao personDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ssh.service.PersonService#addPerson(com.ssh.domain.Person)
	 */
	@Override
	public boolean addPerson(Person person) {
		this.personDao.addPerson(person);
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
