/**
 * 
 */
package com.ssh.dao.impl;

import org.springframework.stereotype.Repository;

import com.common.dao.impl.CommonSimpleJdbcDaoImpl;
import com.common.dao.impl.CommonSimpleJdbcDaoImpl1;
import com.common.domain.Paging;
import com.ssh.dao.PersonDao;
import com.ssh.domain.Person;

/**
 * @Description:人员对象实现
 * @author zjl
 * @date 2014年10月29日 下午3:18:11 
 */
@Repository
public class PersonDaoImpl1 extends CommonSimpleJdbcDaoImpl1  implements PersonDao  {

	/* (non-Javadoc)
	 * @see com.ssh.dao.PersonDao#addPerson(com.ssh.domain.Person)
	 */
	@Override
	public boolean addPerson(Person person) {
	    String sql="insert into person(id,name,age,birth) values(?,?,?,?)";
	    Object[] values={person.getId(),person.getName(),person.getAge(),person.getBirth()};
	    this.getSimpleJdbcTemplate().update(sql, values);
		return true;
	}

	/* (non-Javadoc)
	 * @see com.ssh.dao.PersonDao#searchPersons(com.common.domain.Paging)
	 */
	@Override
	public Paging<Person> searchPersons(Paging<Person> paging) {
		// TODO Auto-generated method stub
		return null;
	}

}
