/**
 * 
 */
package com.ssh.dao.impl;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import com.ssh.dao.MongodbDao;
import com.ssh.domain.FileObject;

/**
 * mongo抽象实现
 * 
 * @author zjl
 */
public abstract class MongodbDaoDummyImpl implements MongodbDao {

	private static Logger logger = LoggerFactory.getLogger(MongodbDaoDummyImpl.class);

	public abstract String getDbname();

	public abstract String getCollectionName();
    
	public abstract Mongo getMongo() throws UnknownHostException, MongoException;

	
	public String insert(GridFSInputFile gridFSInputFile) {
		return null;
	}

	public FileObject findFileById(String id) {
		return null;
	}

	public FileObject findFileByName(String name) {
		return null;
	}

	public String insert(FileObject fileObject) {
		return null;

	}

	public List<GridFSDBFile> findGridFileByQuery(DBObject object){
		return null;
	}
	
	public boolean deleteGridFsFile(DBObject object) {
		return false;
	}

	public DB findDb() {
		Mongo mongo = null;
		String dbName = this.getDbname();
		try {
			mongo = this.getMongo();
		} catch (Exception e) {
			mongo = null;
			logger.error("link to mongodb server is error");
			
		}
		if (mongo == null) {
			return null;
		}
		DB db = mongo.getDB(dbName);
		return db;
	}

	public DBCollection findCollection() {
		Mongo mongo = null;
		DBCollection coll = null;
		String dbName = this.getDbname();
		String collName = this.getCollectionName();
		try {
			mongo = this.getMongo();
		} catch (Exception e) {
			mongo = null;
			logger.error("link to mongodb server is error");
			
		}
		if (mongo == null) {
			return null;
		}
		DB db = mongo.getDB(dbName);
		coll = db.getCollection(collName);
		return coll;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.core.dao.MongodbDao#delete(com.core.bean.DBObject)
	 */
	public boolean delete(DBObject object) {
		if (object == null) {
			return false;
		}
		boolean flag = true;
		DBCollection coll = this.findCollection();
		if (coll == null) {
			return false;
		}

		coll.remove(object);

		return flag;
	}

	public boolean insert(DBObject[] list) {
		if (list == null || list.length < 1) {
			return false;
		}
		boolean flag = true;
		DBCollection coll = this.findCollection();
		if (coll == null) {
			return false;
		}
		coll.insert(list);
		return flag;
	}

	/**
	 * 查询数据
	 * @param object 查询条件
	 * @return  是否添加成功
	 */
	public List<DBObject> find(DBObject object, int beginRecord, int rows, DBObject... sorts){
		if (object == null) {
			return null;
		}
		beginRecord = beginRecord < 0 ? 0 : beginRecord;
		List<DBObject> list = new ArrayList<DBObject>();
		DBCollection coll = this.findCollection();
		DBCursor cursor = coll.find(object);
		if (sorts != null && sorts.length > 0) {
			for (DBObject sort : sorts) {
				cursor.sort(sort);
			}
		}
		cursor.skip(beginRecord);
		cursor.limit(rows);
		while (cursor.hasNext()) {
			DBObject news = cursor.next();
			list.add(news);
		}
		return list;
	}
	
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.core.dao.MongodbDao#insert(com.core.bean.DBObject)
	 */
	public boolean insert(DBObject object) {
		if (object == null) {
			return false;
		}
		boolean flag = true;
		DBCollection coll = this.findCollection();
		if (coll == null) {
			return false;
		}
		coll.insert(object);
		return flag;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.core.dao.MongodbDao#update(com.core.bean.DBObject)
	 */
	public boolean update(DBObject object) {
		if (object == null) {
			return false;
		}
		boolean flag = true;
		DBCollection coll = this.findCollection();
		if (coll == null) {
			return false;
		}
		coll.save(object);
		return flag;
	}


	public GridFSDBFile findGridFileByName(String name) {
		return null;
	}

	public boolean createIndex(List<DBObject> list) {
		if (list == null || list.size() < 1) {
			return false;
		}
		DBCollection coll = this.findCollection();
		for (DBObject dBObject : list) {
			coll.createIndex(dBObject);
		}
		return true;
	}

	public int searchToCount(DBObject object) {
		int count = 0;
		if (object == null) {
			return 0;
		}
		DBCollection coll = this.findCollection();
		DBCursor cursor = coll.find(object);
		count = cursor.count();
		return count;
	}
}
