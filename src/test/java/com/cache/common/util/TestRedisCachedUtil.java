package com.cache.common.util;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;

public class TestRedisCachedUtil {
	
	protected final Logger logger = LoggerFactory.getLogger(TestRedisCachedUtil.class);
	
	@Test
	public void testSetString() {
		Jedis jedis = RedisCachedUtil.newInstance().getJedis();
		String key="test";
		String value="test";
		jedis.set(key, value);
		logger.debug("value=="+jedis.get(key));
		Assert.assertEquals(value, jedis.get(key));
	}
	
	
	@Test
	public void testSetList() {
		Jedis jedis = RedisCachedUtil.newInstance().getJedis();
		String key="test";
		String value="test";
	}
	
}
