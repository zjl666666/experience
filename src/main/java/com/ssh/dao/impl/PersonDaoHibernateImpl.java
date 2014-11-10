/**
 * 
 */
package com.ssh.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.common.dao.impl.HibernateDaoImpl;
import com.common.domain.Paging;
import com.ssh.dao.PersonDao;
import com.ssh.domain.Person;

/**
 * @Description: 
 * @author zjl
 * @date 2014年10月31日 上午10:08:40
 */
@Repository
public class PersonDaoHibernateImpl extends HibernateDaoImpl<Person, String> implements PersonDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ssh.dao.PersonDao#addPerson(com.ssh.domain.Person)
	 */
	@Override
	public boolean addPerson(Person person) {
		this.save(person);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ssh.dao.PersonDao#searchPersons(com.common.domain.Paging)
	 */
	@Override
	public Paging<Person> searchPersons(Paging<Person> paging) {
		
		return paging;
	}

	@Override
	public Person findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> searchPersons(Map<String, String> map) {
		// TODO Auto-generated method stub
		return null;
	}

}
