package com.cache.common.constant;

import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * @Description: 测试缓存枚举类
 * @author zjl
 * @date 2014年8月27日 下午4:22:08
 */
public class TestCacheEnum {

	@Test
	public void testGetEnumKey(){
		CacheEnum newsDetail=CacheEnum.NEWS_DETAIL;
		Assert.assertEquals("newsDetail", newsDetail.getPrefix());
	}
}
