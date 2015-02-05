/**
 * 
 */
package com.rpc.hessian.client.service;

import com.rpc.hessian.client.domain.News;


/**
 * @Description: 发布新闻服务
 * @author zjl
 * @date 2015年2月4日 上午11:43:09 
 */
public interface NewsService {
    
	/**
	 * 发布新闻
	 * @param news
	 * @return
	 */
	 String publishNews(News news);
	 
	 /**
	  * 
	  * @param id 根据id查找新闻
	  * @return
	  */
	 News findNewsById(String id);
	 
	 
}
