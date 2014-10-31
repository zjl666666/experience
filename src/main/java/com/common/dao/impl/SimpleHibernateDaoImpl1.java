package com.common.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.common.util.ReflectionUtils;

/**
 * dao封装
 * @author zjl
 *
 * @param <T>
 * @param <PK>
 */
@SuppressWarnings("unchecked")
public class SimpleHibernateDaoImpl1<T, PK extends Serializable> {
   /**
    * 记录日志
    */
	protected Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * session工厂
     */
	@Resource(name = "sessionFactory1")
	protected SessionFactory sessionFactory;
    
	/**
	 * 待操作的实体对象
	 */
	protected Class<T> entityClass;

	/**
	 * 用于扩展的DAO子类使用的构造函数.
	 * 
	 * 通过子类的范型定义取得对象类型Class.
	 * eg.
	 * public class UserDao extends SimpleHibernateDao<User, Long>
	 */
	public SimpleHibernateDaoImpl1() {
		this.entityClass = ReflectionUtils.getSuperClassGenricType(getClass());
	}

	/**
	 * 用于Service层直接使用SimpleHibernateDAO的构造函数.
	 * eg.
	 * SimpleHibernateDao<User, Long> userDao = new SimpleHibernateDao<User, Long>(sessionFactory, User.class);
	 */
	public SimpleHibernateDaoImpl1(final SessionFactory sessionFactory, final Class<T> entityClass) {
		this.sessionFactory = sessionFactory;
		this.entityClass = entityClass;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * 采用@Autowired按类型注入SessionFactory,当有多个SesionFactory的时候Override本函数.
	 * @param sessionFactory
	 */
//	@Autowired
//	public void setSessionFactory(SessionFactory sessionFactory) {
//		this.sessionFactory = sessionFactory;
//	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * 保存新增或修改的对象.
	 * @param entity  待保存的实体对象
	 */
	public void save(final T entity) {
		Assert.notNull(entity);
		getSession().saveOrUpdate(entity);
		logger.debug("save entity: {}", entity);
	}

	/**
	 * 修改的数据库中存在并且session内存中存在的对象.避免出现NonUniqueObjectException错误
	 * @param entity  待保存的实体对象
	 */
	public void saveMerge(final T entity) {
		Assert.notNull(entity);
		getSession().merge(entity);
		logger.debug("save entity: {}", entity);
	}
	
	
	/**
	 * 删除对象.
	 * 
	 * @param entity 对象必须是session中的对象或含id属性的transient对象.
	 */
	public void delete(final T entity) {
		Assert.notNull(entity);
		getSession().delete(entity);
		logger.debug("delete entity: {}", entity);
	}

	/**
	 * 按id删除对象.
	 * @param id  待删除数据的id
	 */
	public void delete(final PK id) {
		Assert.notNull(id);
		delete(get(id));
		logger.debug("delete entity {},id is {}", entityClass.getSimpleName(), id);
	}

	/**
	 * 按id获取对象.
	 * @param id id值
	 * @return  结果对象
	 */
	public T get(final PK id) {
		Assert.notNull(id);
		return (T) getSession().load(entityClass, id);
	}

	/**
	 *	获取全部对象. 
	 *@return   按属性查找对象列表
	 */
	public List<T> getAll() {
		return findByCriteria();
	}

    
	/**
	 * 按属性查找对象列表,匹配方式为相等.
	 * @param propertyName 属性名称
	 * @param value  属性值
	 * @return   按属性查找对象列表
	 */
	public List<T> findByProperty(final String propertyName, final Object value) {
		Assert.hasText(propertyName);
		Criterion criterion = Restrictions.eq(propertyName, value);
		return findByCriteria(criterion);
	}

	/**
	 * 按属性查找唯一对象,匹配方式为相等.
     * @param propertyName 属性名称
	 * @param value  属性值
	 * @return 结果对象
	 */
	public T findUniqueByProperty(final String propertyName, final Object value) {
		Assert.hasText(propertyName);
		Criterion criterion = Restrictions.eq(propertyName, value);
		return (T) createCriteria(criterion).uniqueResult();
	}

	/**
	 * 按HQL查询对象列表.
	 * @param  hql 查询语句
	 * @param values 数量可变的参数
	 * @return 结果列表
	 */
	public List<T> find(final String hql, final Object... values) {
		return createQuery(hql, values).list();
	}

	/**
	 * 按HQL查询唯一对象.
	 * @param  hql 查询语句
	 * @param values 数量可变的参数
	 * @return 结果对象
	 */
	public Object findUnique(final String hql, final Object... values) {
		return createQuery(hql, values).uniqueResult();
	}

	/**
	 * 按HQL查询Integer类型结果. 
	 *  @param  hql 查询语句
	 * @param values 数量可变的参数
	 * @return 整数返回值
	 */
	public Integer findInt(final String hql, final Object... values) {
		return (Integer) findUnique(hql, values);
	}

	/**
	 * 按HQL查询Long类型结果. 
     * @param  hql 查询语句
	 * @param values 数量可变的参数’
	 * @return 长整数的返回值
	 */
	public Long findLong(final String hql, final Object... values) {
		return (Long) findUnique(hql, values);
	}

	/**
	 * 根据查询HQL与参数列表创建Query对象.
	 * 
	 * 返回对象类型不是Entity时可用此函数灵活查询.
	 * @param queryString 查询字符串
	 * @param values 数量可变的参数
	 * @return  查询对象
	 */
	public Query createQuery(final String queryString, final Object... values) {
		Assert.hasText(queryString);
		Query query = getSession().createQuery(queryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query;
	}

	/**
	 * 按Criteria查询对象列表.
	 * 
	 * @param criterions 数量可变的Criterion.
	 * @return List<T> 结果列表
	 */
	public List<T> findByCriteria(final Criterion... criterions) {
		return createCriteria(criterions).list();
	}

	/**
	 * 根据Criterion条件创建Criteria.
	 * 
	 * 返回对象类型不是Entity时可用此函数灵活查询.
	 * 
	 * @param criterions 数量可变的Criterion.
	 * @return Criteria结果集
	 */
	public Criteria createCriteria(final Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}

    /**
     * 判断对象的属性值在数据库内是否唯一.
	 * 
	 * 在修改对象的情景下,如果属性新修改的值(value)等于属性原来的值(orgValue)则不作比较.
     * @param propertyName  属性名称
     * @param newValue  待判断的值
     * @param orgValue  原来的值
     * @return 是否唯一布尔变量
     */
	public boolean isPropertyUnique(final String propertyName, final Object newValue, final Object orgValue) {
		if (newValue == null || newValue.equals(orgValue))
			return true;
		Object object = findUniqueByProperty(propertyName, newValue);
		return (object == null);
	}

	/**
	 * 取得对象的主键名.
	 * @return 主键名
	 */
	public String getIdName() {
		ClassMetadata meta = getSessionFactory().getClassMetadata(entityClass);
		Assert.notNull(meta, "Class " + entityClass.getSimpleName() + " not define in HibernateSessionFactory.");
		return meta.getIdentifierPropertyName();
	}
}