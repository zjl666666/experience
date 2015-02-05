package com.rpc.hessian.client.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rpc.hessian.client.domain.News;

//指定测试用例的运行器 这里是指定了Junit4
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring/applicationContext-hessian-client.xml" })
public class TestNewsServiceSpringClient {

	@Resource(name = "newsServiceSpringClient")
	private NewsService newsService;
	

	private static final Logger logger = LoggerFactory.getLogger(TestNewsServiceClient.class);

	@Test
	public void testFindNewsById() {
		News news = newsService.findNewsById("1");
		logger.debug("testFindNewsById  " + news.getTitle());

	}
}
