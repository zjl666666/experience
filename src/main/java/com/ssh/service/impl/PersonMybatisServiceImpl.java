/**
 * 
 */
package com.ssh.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.ssh.dao.PersonMybatisDao;
import com.ssh.domain.Person;
import com.ssh.service.PersonMybatisService;

/**
 * @Description: 人员操作service实现
 * @author zjl
 * @date 2014年10月29日 下午4:27:37
 */
@Service
@Transactional(isolation = Isolation.READ_UNCOMMITTED)
public class PersonMybatisServiceImpl implements PersonMybatisService {
	@Resource(name = "personMybatisDao")
	private PersonMybatisDao personDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ssh.service.PersonService#addPerson(com.ssh.domain.Person)
	 */
	@Override
	public boolean addPerson(Person person) {
		this.personDao.addPerson(person);
		String[] strArr=new String[1];
		strArr[1]="test";
		return false;
	}

	@Override
	public List<Person> searchPersons(Map<String, String> map) {
		List<Person> persons = personDao.searchPersons(map);
		return persons;
	}

}
