/**
 * 
 */
package com.ssh.dao.impl;

import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.ssh.client.MongodbNewsImgClient;
import com.ssh.constant.StorageConstant;

/**
 * 新闻网站前台相关图片数据库操作
 * 
 * @author zjl
 * 
 */
public class MongodbDaoNewsImgImpl extends MongodbDaoFileDummyImpl {
	private static final  Logger logger = LoggerFactory.getLogger(MongodbDaoNewsImgImpl.class);
	private MongodbDaoNewsImgImpl() {
	}

	public static class MongodbDaoNewsImgImplInner {
		public static final MongodbDaoNewsImgImpl mongodbDaoNewsImgImpllOnly = new MongodbDaoNewsImgImpl();
	}

	public static MongodbDaoNewsImgImpl getInstance() {
		return MongodbDaoNewsImgImplInner.mongodbDaoNewsImgImpllOnly;
	}

	@Override
	public String getCollectionName() {
		return "fs.files";
	}

	@Override
	public String getDbname() {
		return StorageConstant.MongodbConstant.NEWS_IMG_FILE_DB_NAME;
	}

	@Override
	public Mongo getMongo() throws UnknownHostException, MongoException {
		return MongodbNewsImgClient.getInstance();
	}

	
	
}
