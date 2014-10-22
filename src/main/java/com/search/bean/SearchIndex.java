/**
 * 
 */
package com.search.bean;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 搜索索引条件w
 * 
 * @author zjl
 * @date 2011-6-21
 */
public class SearchIndex {
	/**
	 * 搜索关键字
	 */
	private String keyword;
	
	/**
	 * 搜索人物
	 */
	private String people;
	
	/**
	 * 搜索地点
	 */
	private String place;
	

	/**
	 * 查询类型,事件，音乐，图片，视频等
	 * 若为多个类型，从常量FrontNewsConstant.SearchIndex中加入多个类型
	 */
	private List<String> types;
	
	/**
	 * 排序字段
	 * 从常量FrontNewsConstant.SearchIndex使用
	 */
	private String orderBy;
	/**
	 * 排序的方式
	 * 从常量FrontNewsConstant.SearchIndex使用
	 */
	private String order;
	
	/**
	 * 查询大于该时间的数据，即可实现查询1月内或1周内的新闻,
	 * 例如：查询一周以内的数据，只需要把相对于当前时间的1周前的时间点设置到该字段
	 */
	private Date searchTime;

	/**
	 * 开始记录数
	 */
	private int beginRecord=0;
	
	/**
	 * 返回的行数
	 */
	private int rows=10;
	
	
	
	/**
	 * @return the people
	 */
	public String getPeople() {
		return people;
	}

	/**
	 * @param people the people to set
	 */
	public void setPeople(String people) {
		this.people = people;
	}

	/**
	 * @return the place
	 */
	public String getPlace() {
		return place;
	}

	/**
	 * @param place the place to set
	 */
	public void setPlace(String place) {
		this.place = place;
	}

	/**
	 * @return the types
	 */
	public List<String> getTypes() {
		return types;
	}

	/**
	 * @param types the types to set
	 */
	public void setTypes(List<String> types) {
		this.types = types;
	}

	/**
	 * @return the orderBy
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * @param orderBy the orderBy to set
	 */
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * @return the order
	 */
	public String getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(String order) {
		this.order = order;
	}

	/**
	 * @return the searchTime
	 */
	public Date getSearchTime() {
		return searchTime;
	}

	/**
	 * @param searchTime the searchTime to set
	 */
	public void setSearchTime(Date searchTime) {
		this.searchTime = searchTime;
	}

	/**
	 * @return the keyword
	 */
	public String getKeyword() {
		if (StringUtils.isNotBlank(this.keyword)) {
			
		}
		return keyword;
	}

	/**
	 * @param keyword
	 *            the keyword to set
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

    

	/**
	 * @return the beginRecord
	 */
	public int getBeginRecord() {
		return beginRecord;
	}

	/**
	 * @param beginRecord the beginRecord to set
	 */
	public void setBeginRecord(int beginRecord) {
		this.beginRecord = beginRecord;
	}

	/**
	 * @return the rows
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * @param rows the rows to set
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}
    
	
	
}
