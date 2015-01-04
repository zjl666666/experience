package com.ssh.constant;

import com.ssh.util.MongodbPropertiesUtil;

/**
 * 
 * @Description: 数据库链接相关常量
 * @author zjl
 * @date 2015年1月4日 下午2:54:43
 */
public interface StorageConstant {

	/**
	 * 
	 * @Description: mongodb数据库配置相关配置
	 * @author zjl
	 * @date 2015年1月4日 下午3:05:14
	 */
	interface MongodbConstant {

		/**
		 * 默认客户端连接mongodb服务器连接的个数
		 */
		String DEFAULT_DB_CLIENT_POOL_COUNT = MongodbPropertiesUtil.getProperty("db_client_pool_count");

		/**
		 * 新闻网站前台图片mongodb服务器IP
		 */
		String NEWS_IMG_DB_SERVER_IP = MongodbPropertiesUtil.getProperty("mongodb_news_img_server_ip");

		/**
		 * 新闻网站前台图片服务mongodb file数据库名称
		 */
		String NEWS_IMG_FILE_DB_NAME = MongodbPropertiesUtil.getProperty("mongodb_db_news_img_name");

		/**
		 * url库数据库的名字
		 */
		String URL_DB_SERVER_IP = MongodbPropertiesUtil.getProperty("mongodb_db_url_server_ip");

		/**
		 * url库数据库的名字
		 */
		String URL_DB_NAME = MongodbPropertiesUtil.getProperty("mongodb_db_url_name");

		/**
		 * url库集合的名称
		 */
		String URL_DB_COLL_NAME = MongodbPropertiesUtil.getProperty("mongodb_db_url_coll_name");

	}
}
