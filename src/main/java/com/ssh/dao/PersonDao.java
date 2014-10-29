package com.ssh.dao;

import com.common.domain.Paging;
import com.ssh.domain.Person;

/**
 * 
 * @Description: 人员操作查询
 * @author zjl
 * @date 2014年10月29日 下午3:02:41
 */
public interface PersonDao {

	/**
	 * 添加人员
	 * @param person
	 * @return
	 */
	public boolean addPerson(Person person);
	
	
	/**
	 * 搜索人员
	 * @param person
	 * @return
	 */
	public Paging<Person> searchPersons(Paging<Person> paging);
	
}
