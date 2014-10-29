package com.ssh.service;

import com.common.domain.Paging;
import com.ssh.domain.Person;

/**
 * 
 * @Description: 人员操作service
 * @author zjl
 * @date 2014年10月29日 下午4:26:37
 */
public interface PersonService {
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
