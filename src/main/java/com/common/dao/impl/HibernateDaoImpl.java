package com.common.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.impl.CriteriaImpl;
import org.hibernate.transform.ResultTransformer;
import org.springframework.util.Assert;

import com.common.domain.Paging;
import com.common.domain.PropertyFilter;
import com.common.domain.PropertyFilter.MatchType;
import com.common.util.ReflectionUtils;

/**
 * hibernate封装
 * @author zjl
 * 
 * @param <T>
 * @param <PK>
 */
public class HibernateDaoImpl<T, PK extends Serializable> extends SimpleHibernateDaoImpl<T, PK> {

	/**
	 * 存放形如”a.b.c“ 形式的查询
	 */
	private List<PropertyFilter> fs = new ArrayList<PropertyFilter>(0);
	private List<String> alias = new ArrayList<String>(0);

	/**
	 * 用于扩展的DAO子类使用的构造函数. <p/> 通过子类的范型定义取得对象类型Class. 
	 *
	 */
	public HibernateDaoImpl() {
		super();
	}

	/**
	 * 用于Service层直接使用SimpleHibernateDaoImpl的构造函数. 
	 */
	public HibernateDaoImpl(SessionFactory sessionFactory, Class<T> entityClass) {
		super(sessionFactory, entityClass);
	}

	// 分页查询函数 //

	/**
	 * 分页获取全部对象.
	 */
	public Paging<T> getAll(final Paging<T> Paging) {
		return findByCriteria(Paging);
	}

	/**
	 * 按HQL分页查询. 不支持自动获取总结果数,需用户另行执行查询.
	 * 
	 * @param Paging
	 *            分页参数.仅支持pageSize 和firstResult,忽略其他参数.
	 * @param hql
	 *            hql语句.
	 * @param values
	 *            数量可变的查询参数.
	 * @return 分页查询结果,附带结果列表及所有查询时的参数.
	 */
	@SuppressWarnings("unchecked")
	public Paging<T> find(final Paging<T> Paging, final String hql, final Object... values) {
		Assert.notNull(Paging);

		Query q = createQuery(hql, values);
		setPageParameter(q, Paging);
		List result = q.list();
		Paging.setResultList(result);
		return Paging;
	}

	/**
	 * 按Criteria分页查询.
	 * 
	 * @param Paging
	 *            分页参数.支持pageSize、firstResult和orderBy、order、autoCount参数.
	 *            其中autoCount指定是否动态获取总结果数.
	 * @param criterions
	 *            数量可变的Criterion.
	 * @return 分页查询结果.附带结果列表及所有查询时的参数.
	 */
	@SuppressWarnings("unchecked")
	public Paging<T> findByCriteria(final Paging<T> Paging, final Criterion... criterions) {
		Assert.notNull(Paging);

		Criteria c = createCriteria(criterions);
		// 以下方法是未了支持形如”parent.name“
		if (fs.size() != 0) {
			for (PropertyFilter pf : fs) {
				String p = pf.getPropertyName();
				String[] pns = StringUtils.split(p, ".");
				Class clazz = this.entityClass;
				try {
					for (int count = 0; count < pns.length; count++) {
						String pn = pns[count];
						String parent = "";
						for (int i = 0; i <= count; i++) {
							parent = parent + "." + pns[i];
						}
						parent = parent.replaceFirst(".", "");
						Field field = getDeclaredFired(clazz, pn);
						Class t = field.getType();
						if (!t.getPackage().getName().contains("com.qoocc.admin")) {
							c.add(buildPropertyCriterion(parent, pf.getValue(), pf.getMatchType()));
						} else {
							// 判断alias是否已经存在，如果是则不创建
							if (hasAlias(alias, parent)) {
								clazz = Class.forName(t.getName());
								continue;
							}
							c.createAlias(parent, parent, DetachedCriteria.LEFT_JOIN);
							clazz = Class.forName(t.getName());
							alias.add(parent);
						}

					}
				} catch (Exception e) {
					logger.error(e.getMessage());
					e.printStackTrace();
					continue;
				}
			}
		}
		alias.clear();// 清除
		if (Paging.isAutoCount()) {
			int totalCount = countCriteriaResult(c, Paging);
			Paging.setTotalCount(totalCount);
		}

		setPageParameter(c, Paging);
		List result = c.list();
		Paging.setResultList(result);
		return Paging;
	}

	private Field getDeclaredFired(Class clazz, String name) {
		Field field = null;
		try {
			field = clazz.getDeclaredField(name);
		} catch (NoSuchFieldException e) {
			if (clazz.getSuperclass() != null) {
				field = getDeclaredFired(clazz.getSuperclass(), name);
			} else {
				e.printStackTrace();
			}
		}
		return field;
	}

	/**
	 * 是否存在Alias
	 * 
	 * @param alias
	 * @param single
	 * @return
	 */
	private boolean hasAlias(List<String> alias, String single) {
		boolean hasAlias = false;
		for (String alia : alias) {
			if (alia.equals(single)) {
				hasAlias = true;
				continue;
			}
		}
		return hasAlias;
	}

	/**
	 * 设置分页参数到Query对象,辅助函数.
	 */
	protected Query setPageParameter(final Query q, final Paging<T> Paging) {
		q.setFirstResult(Paging.getFirst());
		q.setMaxResults(Paging.getPageSize());
		return q;
	}

	/**
	 * 设置分页参数到Criteria对象,辅助函数.
	 */
	protected Criteria setPageParameter(final Criteria c, final Paging<T> Paging) {
		c.setFirstResult(Paging.getFirst());
		c.setMaxResults(Paging.getPageSize());

		if (Paging.isOrderBySetted()) {
			String[] orderByArray = StringUtils.split(Paging.getOrderBy(), ',');
			String[] orderArray = StringUtils.split(Paging.getOrder(), ',');

			Assert.isTrue(orderByArray.length == orderArray.length, "分页多重排序参数中,排序字段与排序方向的个数不相等");

			for (int i = 0; i < orderByArray.length; i++) {
				if (Paging.ASC.equals(orderArray[i])) {
					c.addOrder(Order.asc(orderByArray[i]));
				} else {
					c.addOrder(Order.desc(orderByArray[i]));
				}
			}
		}
		return c;
	}

	/**
	 * 执行count查询获得本次Criteria查询所能获得的对象总数.
	 */
	@SuppressWarnings("unchecked")
	protected int countCriteriaResult(final Criteria c, final Paging<T> Paging) {
		CriteriaImpl impl = (CriteriaImpl) c;

		// 先把Projection、ResultTransformer、OrderBy取出来,清空三者后再执行Count操作
		Projection projection = impl.getProjection();
		ResultTransformer transformer = impl.getResultTransformer();

		List<CriteriaImpl.OrderEntry> orderEntries = null;
		try {
			orderEntries = (List) ReflectionUtils.getFieldValue(impl, "orderEntries");
			ReflectionUtils.setFieldValue(impl, "orderEntries", new ArrayList());
		} catch (Exception e) {
			logger.error("不可能抛出的异常:{}", e.getMessage());
		}

		// 执行Count查询
		int totalCount = (Integer) c.setProjection(Projections.rowCount()).uniqueResult();

		// 将之前的Projection,ResultTransformer和OrderBy条件重新设回去
		c.setProjection(projection);

		if (projection == null) {
			c.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		}
		if (transformer != null) {
			c.setResultTransformer(transformer);
		}
		try {
			ReflectionUtils.setFieldValue(impl, "orderEntries", orderEntries);
		} catch (Exception e) {
			logger.error("不可能抛出的异常:{}", e.getMessage());
		}

		return totalCount;
	}

	// 属性条件查询函数 //

	/**
	 * 按属性查找对象列表,支持多种匹配方式.
	 * 
	 * @param matchTypeStr
	 *            目前支持的取值为"EQUAL"与"LIKE".
	 */
	public List<T> findByProperty(final String propertyName, final Object value, String matchTypeStr) {
		MatchType matchType = Enum.valueOf(MatchType.class, matchTypeStr);
		Criterion criterion = buildPropertyCriterion(propertyName, value, matchType);
		return findByCriteria(criterion);
	}

	/**
	 * 按属性过滤条件列表查找对象列表.
	 */
	public List<T> findByFilters(final List<PropertyFilter> filters) {
		Criterion[] criterions = buildPropertyFilterCriterions(filters);
		return findByCriteria(criterions);
	}

	/**
	 * 按属性过滤条件列表分页查找对象.
	 */
	public Paging<T> findByFilters(final Paging<T> Paging, final List<PropertyFilter> filters) {
		Criterion[] criterions = buildPropertyFilterCriterions(filters);
		return findByCriteria(Paging, criterions);
	}

	/**
	 * 按属性条件列表创建Criterion数组,辅助函数.
	 */
	protected Criterion[] buildPropertyFilterCriterions(List<PropertyFilter> filters) {
		List<Criterion> criterionList = new ArrayList<Criterion>();
		fs.clear();
		int i = 0;
		for (PropertyFilter filter : filters) {

			String propertyName = filter.getPropertyName();
			if (propertyName.contains(".")) {
				fs.add(filter);
				continue;
			}
			boolean multiProperty = StringUtils.contains(propertyName, "|");
			if (!multiProperty) { // properNameName中只有一个属性的情况.
				Criterion criterion = buildPropertyCriterion(propertyName, filter.getValue(), filter
						.getMatchType());
				criterionList.add(criterion);
			} else {// properName中包含多个属性的情况,进行or处理.
				Disjunction disjunction = Restrictions.disjunction();
				String[] params = StringUtils.split(propertyName, '|');

				for (String param : params) {
					Criterion criterion = buildPropertyCriterion(param, filter.getValue(), filter
							.getMatchType());
					disjunction.add(criterion);
				}
				criterionList.add(disjunction);
			}
			i++;
		}
		return criterionList.toArray(new Criterion[criterionList.size()]);
	}

	/**
	 * 按属性条件参数创建Criterion,辅助函数.
	 */
	protected Criterion buildPropertyCriterion(final String propertyName, final Object value,
			MatchType matchType) {
		Assert.hasText(propertyName);
		Criterion criterion = null;

		if (MatchType.EQUAL.equals(matchType)) {
			criterion = Restrictions.eq(propertyName, value);
		} else if (MatchType.LIKE.equals(matchType)) {
			criterion = Restrictions.like(propertyName, (String) value, MatchMode.ANYWHERE);
		} else if (MatchType.GE.equals(matchType)) {
			criterion = Restrictions.ge(propertyName, value);
		} else if (MatchType.GT.equals(matchType)) {
			criterion = Restrictions.gt(propertyName, value);
		} else if (MatchType.LE.equals(matchType)) {
			criterion = Restrictions.le(propertyName, value);
		} else if (MatchType.LT.equals(matchType)) {
			criterion = Restrictions.lt(propertyName, value);
		} else if (MatchType.NE.equals(matchType)) {
			criterion = Restrictions.ne(propertyName, value);
		} else if (MatchType.IN.equals(matchType)) {
			// if (value instanceof String) {
			// String valueStr = (String) value;
			// String[] strs = null;
			// if (valueStr != null && !valueStr.trim().equals("")) {
			// strs = valueStr.split(",");
			// }
			// criterion = Restrictions.in(propertyName, strs);
			// } else
			if (value.getClass().isArray()) {
				criterion = Restrictions.in(propertyName, (Object[]) value);
			} else {
				criterion = Restrictions.in(propertyName, (Collection) value);
			}

		}
		return criterion;
	}

	@SuppressWarnings("unchecked")
	protected void setPagedResult(Paging Paging, Criteria criteria) {
		if (Paging.isAutoCount()) {
			int totalCount = countCriteriaResult(criteria, Paging);
			Paging.setTotalCount(totalCount);
		}
		setPageParameter(criteria, Paging);
		List result = criteria.list();
		Paging.setResultList(result);
	}

	// 添加额外的条件(Criterion形式)到propertyFilters条件中
	public Paging<T> searchByFiltersAndCriterions(Paging<T> Paging, List<PropertyFilter> filters,
			Criterion... criterions) {
		ArrayList<Criterion> criterionList = new ArrayList<Criterion>();
		Criterion[] cs = buildPropertyFilterCriterions(filters);

		Collections.addAll(criterionList, cs);
		Collections.addAll(criterionList, criterions);

		Criteria c = createCriteria(criterionList.toArray(new Criterion[criterionList.size()]));

		setPagedResult(Paging, c);

		return Paging;
	}

	// 通过回调Criteria添加查询条件或其他..
	public Paging<T> searchByFiltersAndCallback(Paging<T> Paging, List<PropertyFilter> filters,
			CriteriaCallback callback) {
		Criterion[] criterions = buildPropertyFilterCriterions(filters);
		Criteria c = createCriteria(criterions);
		callback.doInCriteria(c);

		setPagedResult(Paging, c);

		return Paging;
	}

	public static interface CriteriaCallback {
		public void doInCriteria(Criteria c);
	}
}
