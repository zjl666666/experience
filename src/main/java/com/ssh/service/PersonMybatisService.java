package com.ssh.service;

import java.util.List;
import java.util.Map;

import com.common.domain.Paging;
import com.ssh.domain.Person;

/**
 * 
 * @Description: 人员操作service
 * @author zjl
 * @date 2014年10月29日 下午4:26:37
 */
public interface PersonMybatisService {
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
	public List<Person> searchPersons(Map<String,String> map);
}
