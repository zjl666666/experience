package com.common.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.common.domain.Paging;

/**
 * 命名参数Dao基类
 * 
 * @author zjl
 * @date 2011-8-1
 */
@Repository
public class CommonNamedJdbcDaoImpl extends NamedParameterJdbcDaoSupport {
	/**
	 * 记录日志
	 */
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 分页
	 * 
	 * @param <T>结果集中的实体类型
	 * @param sql
	 *            查询sql的语句
	 * @param page
	 *            分页对象
	 * @param beanClass
	 *            分页返回结果集中Class类型
	 * @param param
	 *            参数对象
	 * @return 分页结果集
	 */
	@SuppressWarnings("unchecked")
	public <T>  Paging<T> search(String sql, Paging<T> page, Class<T> beanClass, Object... param) {
		if (StringUtils.isBlank(sql)) {
			return null;
		}
		if (page == null) {
			return null;
		}
		if (beanClass == null) {
			return null;
		}

		Object paramValue = null;
		SqlParameterSource sqlParameterSource = null;
		if (param != null && param.length >= 1) {
			paramValue = param[0];
			sqlParameterSource = new BeanPropertySqlParameterSource(paramValue);
		}

		String countSql = "select count(*) " + sql.substring(sql.indexOf("from"));
		logger.debug("分页统计总条数的语句为===" + countSql);
		int totalCount = 0;
		if (paramValue == null) {
			totalCount = this.getJdbcTemplate().queryForInt(countSql);
		} else {
			totalCount = this.getNamedParameterJdbcTemplate().queryForInt(countSql,
					sqlParameterSource);
		}
		page.setTotalCount(totalCount);

		StringBuilder pageSql = new StringBuilder(sql);
		pageSql.append(" ").append("limit ").append(" ").append(page.getFirst()).append(",")
				.append(page.getPageSize());
		logger.debug("分页的语句为===" + pageSql.toString());
		List<T> result = null;
		if (paramValue == null) {
			result = this.getNamedParameterJdbcTemplate().getJdbcOperations().query(
					pageSql.toString(), new BeanPropertyRowMapper(beanClass));
		} else {
			result = this.getNamedParameterJdbcTemplate().query(pageSql.toString(),
					sqlParameterSource, new BeanPropertyRowMapper(beanClass));
		}
		page.setResultList(result);
		return page;
	}
	
	
	/**
	 * 分页
	 * 
	 * @param <T>结果集中的实体类型
	 * @param sql
	 *            查询sql的语句
	 * @param page
	 *            分页对象
	 * @param beanClass
	 *            分页返回Class
	 * @param param
	 *            参数对象
	 * @return 分页结果集
	 */
	public Paging<Map<String, Object>> search(String sql, Paging<Map<String, Object>> page,
			Object... param) {
		if (StringUtils.isBlank(sql)) {
			return null;
		}
		if (page == null) {
			return null;
		}

		String countSql = "select count(*) " + sql.substring(sql.indexOf("from"));
		logger.info("分页统计总条数的语句为===" + countSql);
		int totalCount = 0;
		if (param != null && param.length >= 1) {
			totalCount = this.getJdbcTemplate().queryForInt(countSql, param);
		} else {
			totalCount = this.getJdbcTemplate().queryForInt(countSql);
		}
		page.setTotalCount(totalCount);

		StringBuilder pageSql = new StringBuilder(sql);
		pageSql.append(" ").append("limit ").append(" ").append(page.getFirst()).append(",")
				.append(page.getPageSize());
		logger.info("分页的语句为===" + pageSql.toString());
		List<Map<String, Object>> result = null;
		if (param != null && param.length >= 1) {
			result = this.getJdbcTemplate().queryForList(pageSql.toString(), param);
		} else {
			result = this.getJdbcTemplate().queryForList(pageSql.toString());
		}
		page.setResultList(result);
		return page;
	}
 	/**
 	 * 更新操作
 	 * @Title:update
 	 * @Description:
 	 * @param sql sql语句
 	 * @param paramMap 参数
 	 * @return
 	 *
 	 */
 	public boolean update(String sql,Map<String, ?> paramMap){
 		  int num = getNamedParameterJdbcTemplate().getJdbcOperations().update(sql, paramMap);
 		  logger.info("受影响的行数为---->"+num);
 		  return num>0;
 	}
 	
	public String packagePagingSql(StringBuilder pagingSql, Paging<?> paging) {
		pagingSql.append(" ").append("limit ").append(" ").append(paging.getFirst()).append(",").append(paging.getPageSize());
		return pagingSql.toString();
	}
	
 	
}
