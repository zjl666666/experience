/**
 * 
 */
package com.mongodb.dao.impl;

import static org.springframework.data.mongodb.core.query.Criteria.*;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Repository;

import com.mongodb.dao.MongodbFileDao;
import com.mongodb.domain.FileObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;

/**
 * @Description: TODO
 * @author zjl
 * @date 2015年1月14日 下午2:59:42
 */
@Repository
public class MongodbFileDaoImpl implements MongodbFileDao {

	@Resource(name = "gridFsTemplate")
	private GridFsTemplate gridFsTemplate;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mongodb.dao.MongodbFileDao#findGridFileByName(java.lang.String)
	 */
	@Override
	public GridFSDBFile findGridFileByName(String name) {
		Query query = new Query();
		query.addCriteria(where("filename").is(name));
		return this.gridFsTemplate.findOne(query);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mongodb.dao.MongodbFileDao#insert(com.mongodb.domain.FileObject)
	 */
	@Override
	public String insert(FileObject fileObject) {
		GridFSFile GridFSFile = this.gridFsTemplate.store(fileObject.getInputStream(), fileObject.getFileName());
		return GridFSFile.getFilename();
	}

}
