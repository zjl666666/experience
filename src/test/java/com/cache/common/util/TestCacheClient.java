package com.cache.common.util;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @Description: 测试缓存客户端
 * @author zjl
 * @date 2014年8月28日 下午2:03:46
 */
public class TestCacheClient {

	protected final Logger logger = LoggerFactory.getLogger(TestCacheClient.class);

	@Test
	public void testSetGetValue() {
		CacheClient cacheClient = CacheClient.getInstance();
		String key = "test";
		String value = "testValue";
		cacheClient.set(key, value, 1800);
		String getValue = (String) cacheClient.get(key);
		Assert.assertEquals(value, getValue);
	}

	@Test
	public void testGetValue() {
		CacheClient cacheClient = CacheClient.getInstance();
		String key = "testddd";
		String value = "testValue";
		String getValue = (String) cacheClient.get(key);
		logger.debug(getValue);
		Assert.assertEquals(null, getValue);
	}

	@Test
	public void testIncr() {
		CacheClient cacheClient = CacheClient.getInstance();
		String key = "test1";
		long value = 5l;
		long incr = cacheClient.incr(key, value);
		String getValue = (String) cacheClient.get(key);
		logger.debug("getValue==" + getValue);
//		Assert.assertEquals(2l, incr);
	}

	@Test
	public void testDecr() {
		CacheClient cacheClient = CacheClient.getInstance();
		String key = "test2";
		long value = 5l;
		long incr = cacheClient.decr(key, value);
//		Assert.assertEquals(2l, incr);
	}

	@Test
	public void testGetMulti() {
		CacheClient cacheClient = CacheClient.getInstance();
		String key1="test4";
		String key2="test6";
		String value1="value1";
		String value2="value2";
		cacheClient.set(key1, value1, 50);
		cacheClient.set(key2, value2, 50);
		String[] keys = { key1, key2 };
		Map<String, Object> values = cacheClient.getMulti(keys);
		
		Assert.assertEquals(value1, values.get(key1));
		Assert.assertEquals(value2, values.get(key2));
	}
}
