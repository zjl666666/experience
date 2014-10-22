package com.cache.common.util;

import org.junit.Assert;
import org.junit.Test;

public class TestCachePropertiesUtil {

	@Test
	public  void testGetConstant(){
		String serverUrl = CachePropertiesUtil.getProperty("ip");
		String[] servers = new String[1];
		servers[0]=serverUrl;
		String real="192.168.1.100:12000";
		Assert.assertEquals(real, servers[0]);
	}
}
