package com.ssh.dao.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import com.ssh.domain.FileObject;

/**
 * 保存文件的虚拟基类实现
 * 
 * @author zjl
 * @date 2011-12-9
 */
public abstract class MongodbDaoFileDummyImpl extends MongodbDaoDummyImpl {
	private static final Logger logger = LoggerFactory.getLogger(MongodbDaoFileDummyImpl.class);

	/**
	 * 根据查询条件删除文件库中的文件
	 */
	public boolean deleteGridFsFile(DBObject object) {
		if (object == null) {
			return false;
		}
		DB db = this.findDb();
		if (db == null) {
			return false;
		}
		GridFS gridFS = new GridFS(db);
		gridFS.remove(object);
		return true;
	}

	/**
	 * 根据id查询文件库中的文件
	 */
	public FileObject findFileById(String id) {
		if (StringUtils.isBlank(id)) {
			return null;
		}
		FileObject fileObject = null;
		DB db = this.findDb();
		if (db == null) {
			return null;
		}
		GridFS gridFS = new GridFS(db);
		GridFSDBFile gridFSDBFile = gridFS.find(new ObjectId(id));

		fileObject = this.getFileObjectByDBFile(gridFSDBFile);

		return fileObject;
	}

	/**
	 * 根据文件名称查询文件库中的文件
	 */
	public GridFSDBFile findGridFileByName(String name) {
		if (StringUtils.isBlank(name)) {
			return null;
		}
		DB db = this.findDb();
		if (db == null) {
			return null;
		}
		GridFS gridFS = new GridFS(db);
		GridFSDBFile gridFSDBFile = gridFS.findOne(name);
		return gridFSDBFile;
	}

	/**
	 * 根据详细条件查询文件库中的文件 2012/1/10 迁移数据使用，因为要调换新闻网站前台的接口，所以要把旧数据迁移到新的图片库中
	 */
	public List<GridFSDBFile> findGridFileByQuery(DBObject object) {
		if (object == null) {
			return null;
		}
		DB db = this.findDb();
		if (db == null) {
			return null;
		}
		GridFS gridFS = new GridFS(db);
		List<GridFSDBFile> gridFSDBFiles = gridFS.find(object);
		return gridFSDBFiles;
	}

	/**
	 * 
	 * @description  查询所有文件的游标
	 * @return
	 * zjl  2014年1月20日下午8:09:59
	 */
	public DBCursor findAllForDBCursor() {
		DBCursor dbCursor = null;
		DBCollection dbCollection=this.findCollection();
		
		Calendar beginDate = Calendar.getInstance();
		Calendar endDate = Calendar.getInstance();
		
		beginDate.set(Calendar.DAY_OF_MONTH, beginDate.get(Calendar.DAY_OF_MONTH)-50);
		
		endDate.set(Calendar.DAY_OF_MONTH, endDate.get(Calendar.DAY_OF_MONTH)-3);
		
		DBObject query = new BasicDBObject();
		
		query.put("uploadDate", new BasicDBObject("$gte", beginDate.getTime()).append("$lte",
				endDate.getTime()));
		
		
		dbCursor=dbCollection.find(query);
		
//		dbCursor=dbCollection.find();
		dbCursor.batchSize(200);
		return dbCursor;
	}

	/**
	 * 根据文件名称查询文件库中的文件
	 */
	public FileObject findFileByName(String name) {
		if (StringUtils.isBlank(name)) {
			return null;
		}
		FileObject fileObject = null;
		DB db = this.findDb();
		if (db == null) {
			return null;
		}
		GridFS gridFS = new GridFS(db);
		GridFSDBFile gridFSDBFile = gridFS.findOne(name);
		fileObject = this.getFileObjectByDBFile(gridFSDBFile);
		return fileObject;
	}

	/**
	 * 添加文件到文件库
	 */
	public String insert(GridFSInputFile gridFSInputFile) {
		gridFSInputFile.save();
		return (String) gridFSInputFile.getId();
	}

	/**
	 * 添加文件到文件库
	 */
	public String insert(FileObject fileObject) {
		if (fileObject == null) {
			return null;
		}
		byte[] data = fileObject.getData();
		if (data == null || data.length < 1) {
			return null;
		}
		String alialses = fileObject.getAliases();
		String fileName = fileObject.getFileName();
		String contentType = fileObject.getContentType();
		String id = fileObject.getId();
		String md5 = fileObject.getMd5();
		Date uploadDate = fileObject.getUploadDate();
		DBObject dbObject = fileObject.getMetaData();

		DB db = this.findDb();
		if (db == null) {
			return null;
		}
		GridFS gridFS = new GridFS(db);
		GridFSInputFile gridFSInputFile = gridFS.createFile(data);

		if (StringUtils.isNotBlank(id)) {
			gridFSInputFile.put("_id", id);
		}
		if (StringUtils.isNotBlank(fileName)) {
			gridFSInputFile.setFilename(fileName);
		}
		if (StringUtils.isNotBlank(contentType)) {
			gridFSInputFile.setContentType(contentType);
		}
		if (uploadDate != null) {
			gridFSInputFile.put("uploadDate", uploadDate);
		}
		if (dbObject != null) {
			gridFSInputFile.put("metadata", dbObject);
		}
		if (StringUtils.isNotBlank(md5)) {
			gridFSInputFile.put("md5", md5);
		}
		if (StringUtils.isNotBlank(alialses)) {
			gridFSInputFile.put("aliases", alialses);
		}
		gridFSInputFile.save();
		return gridFSInputFile != null ? gridFSInputFile.getId().toString() : null;
	}

	/**
	 * 根据gridFSDBFile获取FileObject文件对象
	 * 
	 * @param gridFSDBFile
	 * @return
	 */
	public FileObject getFileObjectByDBFile(GridFSDBFile gridFSDBFile) {
		if (gridFSDBFile == null) {
			return null;
		}
		FileObject fileObject = new FileObject();
		fileObject.setInputStream(gridFSDBFile.getInputStream());
		fileObject.setContentType(gridFSDBFile.getContentType());
		fileObject.setFileName(gridFSDBFile.getFilename());
		fileObject.setId(gridFSDBFile.getId().toString());
		fileObject.setUploadDate(gridFSDBFile.getUploadDate());
		fileObject.setMetaData(gridFSDBFile.getMetaData());
		return fileObject;
	}
}
