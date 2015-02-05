/**
 * 
 */
package com.rpc.hessian.client.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rpc.hessian.client.domain.News;


/**
 * @Description:spring 集成 hessian 发布新闻服务实体
 * @author zjl
 * @date 2015年2月4日 上午11:51:21
 */
@Service
public class NewsServiceSpringClient implements NewsService {

	private static final Logger logger = LoggerFactory.getLogger(NewsServiceSpringClient.class);
	
	@Resource(name = "newsService")
	private NewsService newsService ;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.rpc.hessian.server.client.service.NewsService#publishNews(com.rpc
	 * .hessian.server.domain.News)
	 */
	@Override
	public String publishNews(News news) {
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
		return newsService.findNewsById(id);
	}

}
