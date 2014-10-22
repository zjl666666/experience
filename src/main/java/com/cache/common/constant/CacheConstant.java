package com.cache.common.constant;

import com.cache.common.util.CachePropertiesUtil;

/**
 * 
 * @Description:缓存相关常量配置
 * @author zjl
 * @date 2014年8月26日 下午4:41:57
 */
public interface CacheConstant {
    /**
     * 
     * @Description: 缓存客户端链接参数配置
     * @author zjl
     * @date 2014年8月26日 下午4:42:26
     */
	interface MemcachedParams {
		/**
		 * 缓存服务器的ip和端口
		 */
		String IP=CachePropertiesUtil.getProperty("ip");
		/**
		 * 设置初始连接数100
		 */
		String INIT_CONN = CachePropertiesUtil.getProperty("init_conn");
		/**
		 * 设置每个服务器最少可用连接数
		 */
		String MIN_CONN = CachePropertiesUtil.getProperty("min_conn");
		/**
		 * 设置每个服务器最大可用连接数
		 */
		String MAX_CONN = CachePropertiesUtil.getProperty("max_conn");
		/**
		 * 设置可用连接池的最长等待时间
		 */
		String MAX_SLEEP = CachePropertiesUtil.getProperty("max_sleep");
		/**
		 * 设置socket的读取等待超时值
		 */
		String SOCKET_TO = CachePropertiesUtil.getProperty("socket_to");
	}

}
