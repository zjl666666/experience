package com.rpc.hessian.client.service;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rpc.hessian.client.domain.News;

public class TestNewsServiceClient {
	
	private static final Logger logger = LoggerFactory.getLogger(TestNewsServiceClient.class);
	

	@Test
	public void testFindNewsById(){
		NewsServiceClient client=new NewsServiceClient();
		News news=client.findNewsById("1");	
		logger.debug("testFindNewsById  "+news.getTitle());
		
	}
	
}
