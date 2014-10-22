/**
 * 
 */
package com.search.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.util.DateManagerUtil;
import com.search.bean.NewsIndex;
import com.search.bean.NewsIndexList;
import com.search.bean.SearchIndex;
import com.search.constant.NewsConstant;
import com.search.service.IndexService;
import com.search.util.IndexClientUtil;

/**
 * 前台数据查询
 * 
 * @author zjl
 * 
 */

public class IndexServiceImpl implements IndexService {

	/**
	 * 记录日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(IndexServiceImpl.class);
	public IndexServiceImpl(){
		
	}
//	private IndexServiceImpl() {
//	}

	
	@Override
	public boolean delete(String id) {
		if (StringUtils.isBlank(id)) {
			return false;
		}
		try {
			String query = "id:" + id;
			SolrServer server = IndexClientUtil.newInstance();
			try {
				UpdateResponse response = server.deleteByQuery(query);
			} catch (SolrServerException e) {
				logger.error("删除索引出现错误", e);
			} catch (IOException e) {
				logger.error("删除索引出现错误", e);
			}
		} catch (MalformedURLException e) {
			logger.error("删除索引出现错误", e);
		}
		return true;
	}
	
	
	@Override
	public NewsIndexList searchByEvent(SearchIndex searchIndex) {
		if (searchIndex == null) {
			return null;
		}

		String people = searchIndex.getPeople();
		String place = searchIndex.getPlace();

		if (StringUtils.isBlank(people) && StringUtils.isBlank(place)) {
			return null;
		}

		int beginRecord = searchIndex.getBeginRecord();
		int rows = searchIndex.getRows();
		if (rows > NewsConstant.searchPageSize.SEARCH_INDEX_MAX_COUNT) {
			rows = NewsConstant.searchPageSize.SEARCH_INDEX_MAX_COUNT;
		}
		SolrQuery query = new SolrQuery();
		StringBuilder queryStr = new StringBuilder("");

		if (StringUtils.isNotBlank(people)) {
			queryStr.append("people:").append(people);
		} else if (StringUtils.isNotBlank(place)) {
			queryStr.append("place:").append(place);
		}

		queryStr.append(" ").append("AND ");
		queryStr.append("type:").append(NewsConstant.SearchIndex.EVENT).append(" ");

		query.setQuery(queryStr.toString());
		query.setStart(beginRecord);
		query.setRows(rows);
		NewsIndexList list = this.queryForNewsIndexList(query);
		return list;
	}

	
	
	@Override
	public NewsIndexList search(SearchIndex searchIndex) {
		if (searchIndex == null) {
			return null;
		}
		String title = searchIndex.getKeyword();
		if (StringUtils.isEmpty(title)) {
			return null;
		}
		List<String> types = searchIndex.getTypes();

		int beginRecord = searchIndex.getBeginRecord();
		int rows = searchIndex.getRows();
		if (rows > NewsConstant.searchPageSize.SEARCH_INDEX_MAX_COUNT) {
			rows = NewsConstant.searchPageSize.SEARCH_INDEX_MAX_COUNT;
		}
		SolrQuery query = new SolrQuery();
		StringBuilder queryStr = new StringBuilder("");
		queryStr.append("title:").append(title);
		
		// queryStr.append(" ").append("AND ");
		// queryStr.append("(sts:").append(NewsConstant.StatusType.AUDITED).append(" ");
		// queryStr.append(" ").append("or ");
		// queryStr.append("sts:").append(NewsConstant.StatusType.WAIT_AUDIT).append(") ");
		// queryStr.append(" ").append("NOT ");
		// queryStr.append("id:").append("test").append(" ");
		// queryStr.append(" ").append("NOT ");
		// queryStr.append("id:").append("test").append(" ");
		
//		if (types != null && types.size() > 0 && types.size() < 4) {
//			queryStr.append(" ").append("AND ");
//			for (int i = 0; i < types.size(); i++) {
//				if(i==0){
//					queryStr.append("(");
//				}
//				queryStr.append("type:").append(types.get(i)).append("");
//				if (i == (types.size() - 1)) {
//					queryStr.append(") ");
//				} else {
//					queryStr.append(" ").append("OR ");
//				}
//			}
//		}
		
		Date searchTime = searchIndex.getSearchTime();
		if (searchTime != null) {
			queryStr.append(" ").append("AND ").append("modifyTime:[").append(
					DateManagerUtil.getSolrSearchTime(searchTime)).append(" ").append("TO").append(" ")
					.append("*").append("]");
		}

		String order = searchIndex.getOrder();
		String orderby = searchIndex.getOrderBy();
		if (StringUtils.isNotBlank(orderby)) {
			if (StringUtils.isBlank(order)) {
				query.addSort(orderby, SolrQuery.ORDER.desc);
			} else if (order.equals(NewsConstant.SearchIndex.ORDER_ASC)) {
				query.addSort(orderby, SolrQuery.ORDER.asc);
			} else if (order.equals(NewsConstant.SearchIndex.ORDER_DESC)) {
				query.addSort(orderby, SolrQuery.ORDER.desc);
			}
		}
		query.setQuery(queryStr.toString());
		query.setStart(beginRecord);
		query.setRows(rows);
		NewsIndexList list = this.queryForNewsIndexList(query);
		return list;
	}

	/**
	 * 根据条件查询索引 start:0,1,2...
	 * 
	 * @param query
	 *            查询条件
	 * @return 新闻索引列表
	 */
	private NewsIndexList queryForNewsIndexList(SolrQuery query) {
		if (query == null) {
			return null;
		}
		NewsIndexList newsIndexBeanList = new NewsIndexList();
		List<NewsIndex> list = null;
		try {
			SolrServer server = IndexClientUtil.newInstance();
			QueryResponse response = server.query(query);
			newsIndexBeanList.setAllNum(response.getResults().getNumFound());
			logger.debug("all count==" + response.getResults().getNumFound());
			list = response.getBeans(NewsIndex.class);
			if (list != null) {
				newsIndexBeanList.addAll(list);
			}
		} catch (MalformedURLException e) {
			logger.error("查询索引出现错误", e);
		} catch (SolrServerException e) {
			logger.error("查询索引出现错误", e);
		}
		return newsIndexBeanList;
	}

	/**
	 * 根据条件查询索引的总数
	 * 
	 * @param query
	 *            查询条件
	 * @return
	 */
	private Integer searchSumByQuery(SolrQuery query) {
		if (query == null) {
			return 0;
		}
		int sum = 0;
		try {
			SolrServer server = IndexClientUtil.newInstance();
			query.setRows(2);
			QueryResponse response = server.query(query);
			sum = (int) response.getResults().getNumFound();
			logger.debug("all count==" + sum);
		} catch (MalformedURLException e) {
			logger.error("查询索引出现错误", e);
		} catch (SolrServerException e) {
			logger.error("查询索引出现错误", e);
		}
		return sum;
	}
	

	@Override
	public NewsIndexList searchForWebsite(SearchIndex searchIndex) {
		if (searchIndex == null) {
			return null;
		}
		String title = searchIndex.getKeyword();
		if (StringUtils.isEmpty(title)) {
			return null;
		}
		List<String> types = searchIndex.getTypes();

		int beginRecord = searchIndex.getBeginRecord();
		int rows = searchIndex.getRows();
		if (rows > NewsConstant.searchPageSize.SEARCH_INDEX_MAX_COUNT) {
			rows = NewsConstant.searchPageSize.SEARCH_INDEX_MAX_COUNT;
		}
		SolrQuery query = new SolrQuery();
		StringBuilder queryStr = new StringBuilder("");
		queryStr.append("title:").append(title);
		
		queryStr.append(" ").append("NOT ");
		queryStr.append("type:user").append(" ");
		
		Date searchTime = searchIndex.getSearchTime();
		if (searchTime != null) {
			queryStr.append(" ").append("AND ").append("modifyTime:[").append(
					DateManagerUtil.getSolrSearchTime(searchTime)).append(" ").append("TO").append(" ")
					.append("*").append("]");
		}

		String order = searchIndex.getOrder();
		String orderby = searchIndex.getOrderBy();
		if (StringUtils.isNotBlank(orderby)) {
			if (StringUtils.isBlank(order)) {
				query.addSort(orderby, SolrQuery.ORDER.desc);
			} else if (order.equals(NewsConstant.SearchIndex.ORDER_ASC)) {
				query.addSort(orderby, SolrQuery.ORDER.asc);
			} else if (order.equals(NewsConstant.SearchIndex.ORDER_DESC)) {
				query.addSort(orderby, SolrQuery.ORDER.desc);
			}
		}
		query.setQuery(queryStr.toString());
		query.setStart(beginRecord);
		query.setRows(rows);
		NewsIndexList list = this.queryForNewsIndexList(query);
		return list;
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.index.operator.SolrOperator#add(java.util.List)
	 */
	public boolean add(List<NewsIndex> beans) {
		if (beans == null || beans.size() < 1) {
			return false;
		}
		UpdateResponse response = null;
		try {
			SolrServer server = IndexClientUtil.newInstance();
			server.addBeans(beans);
			response = server.commit();
		} catch (MalformedURLException e) {
			logger.error("添加索引出现错误", e);
		} catch (SolrServerException e) {
			logger.error("添加索引出现错误", e);
		} catch (IOException e) {
			logger.error("添加索引出现错误", e);
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.index.operator.SolrOperator#add(com.index.bean.NewsIndexBean)
	 */
	public boolean add(NewsIndex bean) {
		if (bean == null) {
			return false;
		}
		UpdateResponse response = null;
		try {
			SolrServer server = IndexClientUtil.newInstance();
			server.addBean(bean);
			response = server.commit();
		} catch (MalformedURLException e) {
			logger.error("添加索引出现错误", e);
		} catch (IOException e) {
			logger.error("添加索引出现错误", e);
		} catch (SolrServerException e) {
			logger.error("添加索引出现错误", e);
		}
		return true;
	}

	/**
	 * 优化索引
	 */
	public void optimizeIndex() {
		try {
			SolrServer server = IndexClientUtil.newInstance();
			server.optimize();
		} catch (Exception e) {
			logger.error("优化索引出现错误", e);
		}
	}
}
