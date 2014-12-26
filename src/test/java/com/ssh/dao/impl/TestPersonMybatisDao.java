package com.ssh.dao.impl;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.domain.Paging;
import com.common.util.UuidUtil;
import com.ssh.dao.PersonMybatisDao;
import com.ssh.domain.Person;
import com.ssh.domain.PersonNews;

public class TestPersonMybatisDao{

	/**
	 * 记录日志
	 */
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 获得MyBatis SqlSessionFactory
	 * SqlSessionFactory负责创建SqlSession，一旦创建成功，就可以用SqlSession实例来执行映射语句
	 * ，commit，rollback，close等方法。
	 * 
	 * @return
	 */
	private  SqlSessionFactory getSessionFactory() {
		SqlSessionFactory sessionFactory = null;
		String resource = "mybatis/configuration.xml";
		String dataSourceName="qoocc";
		try {
			sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader(resource),dataSourceName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sessionFactory;
	}

	@Test
	public  void  testAddPerson() {
		SqlSession sqlSession =null;
		try{
			sqlSession=getSessionFactory().openSession();
			PersonMybatisDao PersonMybatisDao = sqlSession.getMapper(PersonMybatisDao.class);
			Person person = new Person();
			person.setId(UuidUtil.getUuid());
			person.setName("zjl");
			person.setBirth(new Date());
			person.setAge(12);
			PersonMybatisDao.addPerson(person);
			sqlSession.commit();
		}finally{
			sqlSession.close();
		}
	}

	@Test
	public  void  testFindById() {
		SqlSession sqlSession =null;
		try{
			sqlSession=getSessionFactory().openSession();
			PersonMybatisDao PersonMybatisDao = sqlSession.getMapper(PersonMybatisDao.class);
		    String id="98048017beb04a1a91e5d4553b0c798c";
		    Person person=PersonMybatisDao.findById(id);
		    logger.debug("person name====================="+person.getName());
		}finally{
			sqlSession.close();
		}
	}
	
	@Test
	public  void  testSearchPersonsByMap() {
		SqlSession sqlSession =null;
		try{
			sqlSession=getSessionFactory().openSession();
			PersonMybatisDao PersonMybatisDao = sqlSession.getMapper(PersonMybatisDao.class);
		    String id="98048017beb04a1a91e5d4553b0c798c";
		    Map<String,String> map=new HashMap<String,String>();
		    map.put("name", "zjl");
		    List<Person> persons=PersonMybatisDao.searchPersons(map);
		    for(Person person:persons){
		    	logger.debug("person name====================="+person.getName());
		    	List<PersonNews> personNews=person.getPersonNews();
		    	if(personNews!=null){
		    		for(PersonNews personNew:personNews){
		    			logger.debug("personNew name====================="+personNew.getNewsName());
		    		}
		    	}
		    }
		}finally{
			sqlSession.close();
		}
	}
	
	@Test
	public  void  testSearchPersonsAssociation() {
		SqlSession sqlSession =null;
		try{
			sqlSession=getSessionFactory().openSession();
			PersonMybatisDao PersonMybatisDao = sqlSession.getMapper(PersonMybatisDao.class);
		    String id="98048017beb04a1a91e5d4553b0c798c";
		    Map<String,String> map=new HashMap<String,String>();
		    map.put("name", "zjl");
		    List<Person> persons=PersonMybatisDao.searchPersonsAssociation(map);
		    for(Person person:persons){
		    	logger.debug("person name====================="+person.getName());
		    	List<PersonNews> personNews=person.getPersonNews();
		    	if(personNews!=null){
		    		for(PersonNews personNew:personNews){
		    			logger.debug("personNew name====================="+personNew.getNewsName());
		    		}
		    	}
		    }
		}finally{
			sqlSession.close();
		}
	}
	
	
	@Test
	public  void  testSearchPersonsByPaging() {
		SqlSession sqlSession =null;
		try{
			sqlSession=getSessionFactory().openSession();
			PersonMybatisDao PersonMybatisDao = sqlSession.getMapper(PersonMybatisDao.class);
		    String id="98048017beb04a1a91e5d4553b0c798c";
		    Paging<Person> paging=new Paging<Person>();
		    Paging<Person> persons=PersonMybatisDao.searchPersons(paging);
//		    for(Person person:persons.getResultList()){
//		    	logger.debug("person name====================="+person.getName());
//		    }
		}finally{
			sqlSession.close();
		}
	}
	
	
	
	@Test
	public  void  testSearchPersonsByPage() {
		SqlSession sqlSession =null;
		try{
			sqlSession=getSessionFactory().openSession();
			PersonMybatisDao PersonMybatisDao = sqlSession.getMapper(PersonMybatisDao.class);
		    String id="98048017beb04a1a91e5d4553b0c798c";
		    Map<String,Object> map=new HashMap<String,Object>();
		    map.put("name", "zjl");
		    map.put("begin", 0);
		    map.put("end", 3);
		    List<Person> persons=PersonMybatisDao.searchPersonsByPage(map);
		    for(Person person:persons){
		    	logger.debug("person name====================="+person.getName());
		    	List<PersonNews> personNews=person.getPersonNews();
		    	if(personNews!=null){
		    		for(PersonNews personNew:personNews){
		    			logger.debug("personNew name====================="+personNew.getNewsName());
		    		}
		    	}
		    }
		}finally{
			sqlSession.close();
		}
	}
	
}
