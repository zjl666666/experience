package com.common.service;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.common.dao.impl.HibernateDaoImpl;
import com.common.domain.Paging;
import com.common.domain.PropertyFilter;
import com.common.util.ReflectionUtils;

/**
 * service封装
 * 
 * @author zjl
 * @date 2011-6-27
 */
@Service
@Transactional(isolation =Isolation.READ_UNCOMMITTED)
public abstract class EntityServiceImpl<T, PK extends Serializable> {

	/**
	 * 记录日志
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 实体类
	 */
	protected Class<T> entityClass;

	/**
	 * 通过子类的范型定义取得领域对象类型Class.
	 * 
	 * eg. public class UserManager extends EntityManager<User, Long>
	 */
	@SuppressWarnings("unchecked")
	public EntityServiceImpl() {
		this.entityClass = ReflectionUtils.getSuperClassGenricType(getClass());
	}

	/**
	 * 根据id删除数据
	 * 
	 * @param id
	 *            id值
	 */
	public void delete(PK id) {
		getEntityDao().delete(id);
	}

	// CRUD函数 //

	/**
	 * 根据id查询
	 * 
	 * @param id
	 *            id
	 * @return 实体对象
	 */
	public T get(PK id) {
		return getEntityDao().get(id);
	}

	/**
	 * 查询所有数据
	 * 
	 * @return 数据列表
	 */
	public List<T> getAll() {
		return getEntityDao().getAll();
	}

	/**
	 * 根据分页参数查询数据
	 * 
	 * @param page
	 *            分页参数
	 * @return 分页对象
	 */
	public Paging<T> getAll(Paging<T> page) {
		return getEntityDao().getAll(page);
	}

	/**
	 * 保存数据
	 * 
	 * @param entity
	 *            实体对象
	 */
	public void save(T entity) {
		getEntityDao().save(entity);
	}

	/**
	 * 根据详细条件查询数据
	 * 
	 * @param page
	 *            分页对象
	 * @param filters
	 *            过滤条件
	 * @return 数据列表
	 */
	public Paging<T> search(Paging<T> page, List<PropertyFilter> filters) {
		return getEntityDao().findByFilters(page, filters);
	}

	/**
	 * 在子类实现的回调函数,为EntityManager提供默认CRUD操作所需的DAO.
	 */
	protected abstract HibernateDaoImpl<T, PK> getEntityDao();
}
