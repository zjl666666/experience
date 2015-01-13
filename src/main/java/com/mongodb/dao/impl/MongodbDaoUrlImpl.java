/**
 * 
 */
package com.mongodb.dao.impl;

import java.net.UnknownHostException;

import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.ssh.client.MongodbUrlClient;
import com.ssh.constant.StorageConstant;

/**
 * @author zjl
 * 
 */
public class MongodbDaoUrlImpl extends MongodbDaoDummyImpl {

	private MongodbDaoUrlImpl() {
	}

	private static class MongodbDaoImpl {
		private static final MongodbDaoUrlImpl mongoImpl = new MongodbDaoUrlImpl();
	}

	public static MongodbDaoUrlImpl getInstance() {
		return MongodbDaoImpl.mongoImpl;
	}

	@Override
	public String getCollectionName() {
		return StorageConstant.MongodbConstant.URL_DB_COLL_NAME;
	}
	
	@Override
	public String getDbname() {
		return StorageConstant.MongodbConstant.URL_DB_NAME;
	}

	@Override
	public Mongo getMongo() throws UnknownHostException, MongoException {
		return MongodbUrlClient.getInstance();
	}

}
