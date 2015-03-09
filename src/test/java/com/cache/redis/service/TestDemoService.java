package com.cache.redis.service;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring/applicationContext-redis.xml" })
public class TestDemoService {

	@Resource(name = "demoService")
	private DemoService demoService;

	private String key = "test";
	private String value = "testValue";

	@Test
	public void testSetString() {
		demoService.setString(key, value);
	}

	@Test
	public void testGetString() {
		String getValue = demoService.getString(key);
		Assert.assertEquals(value, getValue);
	}
}
