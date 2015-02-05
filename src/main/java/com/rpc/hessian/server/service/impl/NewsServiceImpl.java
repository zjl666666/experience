/**
 * 
 */
package com.rpc.hessian.server.service.impl;

import com.caucho.hessian.server.HessianServlet;
import com.rpc.hessian.server.domain.News;
import com.rpc.hessian.server.service.NewsService;

/**
 * @Description: 发布新闻服务实现
 * @author zjl
 * @date 2015年2月4日 上午11:48:03
 */
public class NewsServiceImpl extends HessianServlet implements NewsService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.rpc.hessian.server.service.NewsService#publishNews(com.rpc.hessian
	 * .server.domain.News)
	 */
	@Override
	public String publishNews(News news) {
		if (news == null) {
			return null;
		}
		return news.getId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.rpc.hessian.server.service.NewsService#findNewsById(java.lang.String)
	 */
	@Override
	public News findNewsById(String id) {
		News news = new News();
		news.setId("test");
		news.setTitle("测试hessian发布服务");
		return news;
	}

}
