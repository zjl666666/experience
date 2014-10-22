package com.cache.common.util;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.danga.MemCached.MemCachedClient;

/**
 * 
 * @Description: 缓存操作分装后的工具类
 * @author zjl
 * @date 2014年8月27日 下午2:32:31
 */
public class CacheClient {

	/**
	 * 日志记录
	 */
	protected final Logger logger = LoggerFactory.getLogger(CacheClient.class);

	/**
	 * 缓存客户端操作工作类
	 */
	private MemCachedClient memcachedClient = MemCachedClientUtil.getMemcachedClient();

	private CacheClient() {
		
	}

	private static class CacheClientInstance {
		private static final CacheClient cacheClient = new CacheClient();
	}

	public static CacheClient getInstance() {
		return CacheClientInstance.cacheClient;
	}

	/**
	 * 根据Key返回缓存中对应的数据
	 * 
	 * @param key
	 *            缓存的Key
	 * @return 缓存的数据,如果不存在null值（不是字符串"null")
	 */
	public String get(String key) {
		try {
			return (String) memcachedClient.get(key);
		} catch (Exception e) {
			logger.error("key是" + key + "获取缓存失败", e);
			return null;
		}
	}

	/**
	 * 根据Key设置缓存中对应的数据,如果不需要时间限制，过期值设置为0
	 * 
	 * @param key
	 *            缓存的Key
	 * @param value
	 *            缓存的数据
	 * @param expiration
	 *            失效时间，单位是秒
	 * @return 设置是否成功
	 */
	public boolean set(String key, String value, int expiration) {
		try {
			if (expiration != 0) {
				Calendar expirationCalendar = Calendar.getInstance();
				expirationCalendar.add(Calendar.SECOND, expiration);
				return memcachedClient.set(key, value, expirationCalendar.getTime());
			} else {
				return memcachedClient.set(key, value);
			}
		} catch (Exception e) {
			logger.error("key是" + key + "设置缓存失败", e);
			return false;
		}
	}

	/**
	 * 根据Key删除缓存中对应的数据
	 * 
	 * @param key
	 *            缓存的Key
	 * @return 删除是否成功
	 */
	public boolean delete(String key) {
		try {
			logger.debug("删除key========================================================"+key);
			return memcachedClient.delete(key);
		} catch (Exception e) {
			logger.error("key是" + key + "删除缓存失败", e);
			return false;
		}
	}

	/**
	 * 计数器--》增加，若第一次使用key的值不存在，默认增加一个值为value
	 * 
	 * @param key
	 *            被计数的key
	 * @param value
	 *            在原来的基础上被增加的值
	 * @return 最终的值，若key不存在返回-1
	 */
	public long incr(String key, long value) {
		try {
			return memcachedClient.addOrIncr(key, value);
		} catch (Exception e) {
			logger.error("key是" + key + "计数器增加key的值失败", e);
			return 0l;
		}
	}

	/**
	 * 计数器--》减少，若第一次使用key的值不存在，默认增加一个值为value
	 * 
	 * @param key
	 *            被计数的key
	 * @param value
	 *            在原来的基础上被减少的值
	 * @return 最终的值，若key不存在返回-1
	 */
	public long decr(String key, long value) {
		try {
			return memcachedClient.addOrDecr(key, value);
		} catch (Exception e) {
			logger.error("key是" + key + "计数器减少key的值失败", e);
			return 0l;
		}
	}

	/**
	 * 根据多个Key返回缓存中对应的数据
	 * 
	 * @param key
	 *            缓存的Key数组
	 * @return
	 */
	public Map<String, Object> getMulti(String[] keys) {
		try {
			return (Map<String, Object>) memcachedClient.getMulti(keys);
		} catch (Exception e) {
			logger.error("key是" + keys + "获取缓存失败", e);
			return null;
		}
	}

}
