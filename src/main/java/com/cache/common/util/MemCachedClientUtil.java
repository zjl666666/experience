package com.cache.common.util;

import org.apache.commons.lang.StringUtils;

import com.cache.common.constant.CacheConstant;
import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

/**
 * MemCached缓存客户端工具类,每行数据大小不能超过1M，每个进程不能超过2G
 * 
 * @author zjl
 * 
 */
class MemCachedClientUtil {
	/**
	 * Memcached实例的名称
	 */
	static String poolName = "cache";

	/**
	 * 缓存客户端
	 */
	static MemCachedClient memCachedClient = null;

	static {
		/** 初始化SockIOPool，管理memcached的连接池 */
		// =======================测试环境=======================
		String serverUrl = CacheConstant.MemcachedParams.IP;
		String[] serverUrlArray = null;
		if (StringUtils.isNotBlank(serverUrl)) {
			serverUrlArray = serverUrl.split(",");
		}
		String[] servers = null;
		if (serverUrlArray != null && serverUrlArray.length > 0) {
			servers = new String[serverUrlArray.length];
			for (int i = 0; i < serverUrlArray.length; i++) {
				servers[i] = serverUrlArray[i];
			}
		}
		SockIOPool pool = SockIOPool.getInstance(poolName);
		pool.setServers(servers);
		pool.setFailover(true);
		/** 设置初始连接数100 */
		pool.setInitConn(Integer.valueOf(CacheConstant.MemcachedParams.INIT_CONN));
		// 设置每个服务器最少可用连接数
		pool.setMinConn(Integer.valueOf(CacheConstant.MemcachedParams.MIN_CONN));
		// 设置每个服务器最大可用连接数
		pool.setMaxConn(Integer.valueOf(CacheConstant.MemcachedParams.MAX_CONN));
		// 设置可用连接池的最长等待时间
		pool.setMaintSleep(Integer.valueOf(CacheConstant.MemcachedParams.MAX_SLEEP));
		// 设置是否使用Nagle算法，因为我们的通讯数据量通常都比较大（相对TCP控制数据）而且要求响应及时，因此该值需要设置为false（默认是true）
		pool.setNagle(false);
		// 设置socket的读取等待超时值
		pool.setSocketTO(Integer.valueOf(CacheConstant.MemcachedParams.SOCKET_TO));
		// 设置连接心跳监测开关。
		// 设为true则每次通信都要进行连接是否有效的监测，造成通信次数倍增，加大网络负载，因此该参数应该在对HA要求比较高的场合设为TRUE，默认状态是false。
		pool.setAliveCheck(false);
		
		//集群采用 一致性算法
//        pool.setHashingAlg(SockIOPool.CONSISTENT_HASH);
		pool.initialize();
	}

	/**
	 * 
	 * @description 构建缓存客户端实例
	 * @return zjl 2013-6-27下午2:54:08
	 */
	static MemCachedClient getMemcachedClient() {

		if (memCachedClient == null) {
			/* 建立MemcachedClient实例 */
			memCachedClient = new MemCachedClient(poolName);
		}
		return memCachedClient;
	}

}
