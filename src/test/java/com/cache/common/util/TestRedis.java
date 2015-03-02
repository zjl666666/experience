package com.cache.common.util;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;

public class TestRedis {
	private static final Logger logger = LoggerFactory.getLogger(TestRedis.class);
	private Jedis redis = null;

	/**
	 * 测试前初始化
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() {
		redis = new Jedis("192.168.20.126", 6379);// 连接redis

	}

	@Test
	public void testList() {
		String listKey = "listKey";
		String value = "ddd";
		String value1 = "ddd1";

		long delLength=redis.del(listKey);
		logger.debug("delLength===" + delLength);
		long length = redis.lpush(listKey, value);
		logger.debug("length===" + length);
		length = redis.lpush(listKey, value1);
		logger.debug("length===" + length);
		String lpopValue=redis.lpop(listKey);
		logger.debug("lpopValue===" + lpopValue);
		Assert.assertEquals(value1, lpopValue);
		
	}

	@Test
	public void testString() {
		String key = "name";
		String value = "wangjun";

		String key1 = "name1";
		String value1 = "wangjun1";

		String status = redis.set(key, value);
		logger.debug("redis.set(key, value) status=" + status);
		String getValue = redis.get(key);

		Assert.assertEquals(value, getValue);

		String status1 = redis.mset(key, value, key1, value1);
		logger.debug("redis.mset(key,value,key1,value1) status=" + status1);

		List<String> mgetString = redis.mget(key, key1);
		Assert.assertEquals(value, mgetString.get(0));
		Assert.assertEquals(value1, mgetString.get(1));

	}
}
