/**
 * 
 */
package com.ssh.client;

import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.ssh.constant.StorageConstant;

/**
 *  新闻网站前台图片服务器mongodb数据库客户端
 * 
 * @author zjl
 * 
 */
public class MongodbUrlClient {
	private static final Logger logger = LoggerFactory.getLogger(MongodbUrlClient.class);

	private static Mongo mongo = null;

	private MongodbUrlClient() {
	}

	public static Mongo getInstance() throws UnknownHostException,
			MongoException {
		System.setProperty("MONGO.POOLSIZE", StorageConstant.MongodbConstant.DEFAULT_DB_CLIENT_POOL_COUNT);
		if (mongo == null) {
			String dbAddress = StorageConstant.MongodbConstant.URL_DB_SERVER_IP;
			logger.debug("mongodb dbAddress======="+dbAddress);
			mongo = new Mongo(dbAddress);
		}
		return mongo;
	}

}
