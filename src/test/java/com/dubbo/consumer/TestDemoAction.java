package com.dubbo.consumer;

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
@ContextConfiguration(locations = { "/spring/applicationContext-dubbo-consumer.xml" })
public class TestDemoAction {

	@Resource(name = "demoAction")
	private DemoAction demoAction;
	

	private static final Logger logger = LoggerFactory.getLogger(TestDemoAction.class);

	@Test
	public void testStart() throws Exception {
		demoAction.start();
		Thread.sleep(1000000l);
	}
}
