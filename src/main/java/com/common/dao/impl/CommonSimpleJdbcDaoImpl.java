package com.common.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.common.domain.Paging;

/**
 * jdbc公共类
 * 
 * @author zjl
 * @date 2011-6-8
 */
@Repository
public class CommonSimpleJdbcDaoImpl extends SimpleJdbcDaoSupport {
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
		logger.debug("分页统计总条数的语句为===" + countSql);
		int totalCount = 0;
		if (param != null && param.length >= 1) {
			totalCount = this.getSimpleJdbcTemplate().queryForInt(countSql, param);
		} else {
			totalCount = this.getSimpleJdbcTemplate().queryForInt(countSql);
		}
		page.setTotalCount(totalCount);

		StringBuilder pageSql = new StringBuilder(sql);
		pageSql.append(" ").append("limit ").append(" ").append(page.getFirst()).append(",")
				.append(page.getPageSize());
		logger.debug("分页的语句为===" + pageSql.toString());
		List<Map<String, Object>> result = null;
		if (param != null && param.length >= 1) {
			result = this.getSimpleJdbcTemplate().queryForList(pageSql.toString(), param);
		} else {
			result = this.getSimpleJdbcTemplate().queryForList(pageSql.toString());
		}
		page.setResultList(result);

		return page;
	}
	/**
	 * @description 	分页操作
	 * @param <T>
	 * @param sql
	 * @param page
	 * @param beanClass
	 * @param param
	 * @return
	 * congge 2012-2-11下午02:33:27	
	 */
	@SuppressWarnings("unchecked")
	public <T>  Paging<T> search(String sql,  Paging<T> page,Class<T> beanClass, Object... param)
	{
		if (StringUtils.isBlank(sql)) {
			return null;
		}
		if (page == null) {
			return null;
		}
		if(beanClass==null){
			return null;
		}
		   String countSql = "select count(*) " + sql.substring(sql.indexOf("from"));
			logger.debug("分页统计总条数的语句为===" + countSql);
			// 查询总记录数	
			int totalCount = 0;
			if (param == null) {
				totalCount = this.getJdbcTemplate().queryForInt(countSql);
			} else{
				totalCount = this.getJdbcTemplate().queryForInt(countSql, param);
			}
			page.setTotalCount(totalCount);
			//分页语句封装. 拼接 limit ? ,? 参数
			StringBuilder pageSql = new StringBuilder(sql);
			pageSql.append(" ").append("limit ").append(" ").append(page.getFirst()).append(",")
					.append(page.getPageSize());
			logger.debug("分页的语句为===" + pageSql.toString());
			List<T> result = null;
			if (param != null&&param.length >= 1) {
				result = this.getJdbcTemplate().query(pageSql.toString(), new BeanPropertyRowMapper(beanClass),param);
			}else{
				result = this.getJdbcTemplate().query(pageSql.toString(),new BeanPropertyRowMapper(beanClass));
			}
			//将查询出得结果 设置到resultList
			page.setResultList(result);
			
			 /*ResultSetWrappingSqlRowSet sqlRowSet = (ResultSetWrappingSqlRowSet) this.getJdbcTemplate()   
			                .queryForRowSet(sql, param);*/
			return page;
		
	}
	
	/**
	 * @description 	simplejdbc 基类 的公用的修改操作. 返回受影响的行数 .行数大于0 表示执行成功
	 * @param sql
	 * @param paramMap
	 * @return
	 * congge 2011-10-21下午04:22:22	
	 */
	public boolean update(String sql,Map<String, ?> paramMap){
		  int num = getSimpleJdbcTemplate().getJdbcOperations().update(sql, paramMap);
		  logger.info("受影响的行数为---->"+num);
		  return num>0;
	}


}
