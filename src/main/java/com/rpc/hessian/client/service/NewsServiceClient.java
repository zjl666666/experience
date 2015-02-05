/**
 * 
 */
package com.rpc.hessian.client.service;

import java.net.MalformedURLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.caucho.hessian.client.HessianProxyFactory;
import com.rpc.hessian.client.domain.News;


/**
 * @Description: 发布新闻服务实体
 * @author zjl
 * @date 2015年2月4日 上午11:51:21
 */
public class NewsServiceClient implements NewsService {

	private final String url = "http://localhost:8080/qoocc/newsService";

	private static final Logger logger = LoggerFactory.getLogger(NewsServiceClient.class);

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.rpc.hessian.server.client.service.NewsService#publishNews(com.rpc
	 * .hessian.server.domain.News)
	 */
	@Override
	public String publishNews(News news) {
		HessianProxyFactory pf = new HessianProxyFactory();
		NewsService newsService = null;
		try {
			newsService = (NewsService) pf.create(NewsService.class,url);
		} catch (MalformedURLException e) {
			logger.error("发布新闻出现错误"+e);
		}
		newsService.publishNews(news);
		return news.getId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.rpc.hessian.server.client.service.NewsService#findNewsById(java.lang
	 * .String)
	 */
	@Override
	public News findNewsById(String id) {
		HessianProxyFactory pf = new HessianProxyFactory();
		NewsService newsService = null;
		try {
			newsService = (NewsService) pf.create(NewsService.class,url);
		} catch (MalformedURLException e) {
			logger.error("根据id查询新闻出现错误"+e);
		} 
		return newsService.findNewsById(id);
	}

}
