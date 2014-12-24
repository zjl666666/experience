package com.ssh.dao;

import java.util.List;
import java.util.Map;

import com.common.domain.Paging;
import com.ssh.domain.Person;

/**
 * 
 * @Description: 人员操作查询
 * @author zjl
 * @date 2014年10月29日 下午3:02:41
 */
public interface PersonMybatisDao extends PersonMybatisBaseDao{

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
	
	
	/**
	 * 搜索人员
	 * @param person
	 * @return
	 */
	public List<Person> searchPersons(Map<String,String> map);
	
	/**
	 * 
	 * @param 根据id查询人员
	 * @return
	 */
	public Person findById(String id);
	
}
