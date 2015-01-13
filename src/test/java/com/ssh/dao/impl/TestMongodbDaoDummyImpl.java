package com.ssh.dao.impl;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.dao.MongodbDao;
import com.mongodb.dao.impl.MongodbDaoUrlImpl;

public class TestMongodbDaoDummyImpl {
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void testInsert() {
		MongodbDao mongodb = MongodbDaoUrlImpl.getInstance();
		DBObject object = new BasicDBObject();
		object.put("url", "http://www.qoocc.com");
		object.put("modifyTime", new Date());
		mongodb.insert(object);
	}

	@Test
	public void testFind() {
		MongodbDao mongodb = MongodbDaoUrlImpl.getInstance();
		DBObject object = new BasicDBObject();
		object.put("url", "http://www.qoocc.com");
		List<DBObject> list = mongodb.find(object, 0, 10);
		for(DBObject dbObject:list){
			logger.debug((String)dbObject.get("url"));
		}
	}
}
