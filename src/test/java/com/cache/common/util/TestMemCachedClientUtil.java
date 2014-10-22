package com.cache.common.util;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.danga.MemCached.MemCachedClient;


/**
 * @Description: 缓存工具类的测试
 * @author zjl
 * @date 2014年8月26日 下午5:48:45
 */
public class TestMemCachedClientUtil {

	protected final Logger logger = LoggerFactory.getLogger(TestMemCachedClientUtil.class);

	@Test
	public void testSetCache() {
		MemCachedClient memCachedClient = MemCachedClientUtil.getMemcachedClient();
		String key = "test";
		String value = "testValue";
		memCachedClient.set(key, value);
		String getValue = (String) memCachedClient.get(key);
		logger.debug("getValue===" + getValue);
		Assert.assertEquals(value, getValue);
	}
}
