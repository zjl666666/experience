package com.cache.common.constant;

/**
 * 
 * @Description: 缓存key和过期时间设置
 * @author zjl
 * @date 2014年8月27日 下午3:29:18
 */
public enum CacheEnum {
	/**
	 * 新闻详情
	 */
	NEWS_DETAIL("newsDetail", 5000),

	/**
	 * 新闻列表
	 */
	NEWS_LIST("newsList", 5000);

	/**
	 * 缓存的key前缀
	 */
	private String prefix;

	/**
	 * 缓存过期的时间,单位秒
	 */
	private int expiredTime;

	/**
	 * @param prefix
	 *            缓存的key前缀
	 * @param expiredTime
	 *            过期时间，以秒为单位
	 */
	CacheEnum(String prefix, int expiredTime) {
		this.prefix = prefix;
		this.expiredTime = expiredTime;
	}

	public String getPrefix() {
		return prefix;
	}

	public int getExpiredTime() {
		return expiredTime;
	}
}
