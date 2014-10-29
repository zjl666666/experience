package com.common.service;

import java.io.Serializable;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.common.dao.impl.HibernateDaoImpl;

/**
 * 默认的service基类
 * 
 * @author zjl
 * @date 2011-6-27
 */
@Service
@Transactional(isolation =Isolation.READ_UNCOMMITTED)
public class DefaultEntityServiceImpl<T, PK extends Serializable> extends EntityServiceImpl<T, PK> {
	/**
	 * dao object
	 */
	protected HibernateDaoImpl<T, PK> entityDao;// Default DAO.

	/**
	 * 根据id数据删除数据
	 * 
	 * @param ids
	 *            要删除数据的id数据
	 */
	public void delete(PK[] ids) {
		if (ids == null)
			return;
		for (PK id : ids)
			getEntityDao().delete(id);
	}

	/**
	 * 通过注入的sessionFactory初始化Default Dao.
	 * 
	 * @param sessionFactory
	 *            数据源工厂
	 */
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		entityDao = new HibernateDaoImpl<T, PK>(sessionFactory, entityClass);
	}

	/**
	 * 实现回调函数,为EntityService基类的CRUD操作提供DAO.
	 */
	protected HibernateDaoImpl<T, PK> getEntityDao() {
		return entityDao;
	}
}
