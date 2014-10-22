/**
 * 
 */
package com.search.service;

import com.search.bean.NewsIndexList;
import com.search.bean.SearchIndex;

/**
 * 索引操作
 * 
 * @author zjl
 * 
 */
public interface IndexService {

	/**
	 * 根据新闻id删除索引
	 * 
	 * @param query
	 *            查询条件
	 * @return 删除是否成功
	 */
	public boolean delete(String id);
	
	/**
	 * 根据查询条件查询前台数据，手机接口搜索的时候使用
	 * 
	 * @param searchIndex
	 *            查询条件
	 * @return 返回的事件索引
	 */
	public NewsIndexList search(SearchIndex searchIndex);
	
	
	/**
	 * 根据查询条件查询前台数据，前台网站搜索的时候使用
	 * 
	 * @param searchIndexz
	 *            查询条件
	 * @return 返回的事件索引
	 */
	public NewsIndexList searchForWebsite(SearchIndex searchIndex);

	/**
	 * 根据传来的人物，地点查询相关的事件，事件详细页面查询使用
	 * 
	 * @param searchIndex
	 * @return 返回的事件索引
	 */
	public NewsIndexList searchByEvent(SearchIndex searchIndex);
}
