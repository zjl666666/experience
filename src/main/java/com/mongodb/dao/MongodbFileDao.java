package com.mongodb.dao;

import com.mongodb.domain.FileObject;
import com.mongodb.gridfs.GridFSDBFile;

/**
 * 
 * @Description: 文件操作的接口
 * @author zjl
 * @date 2015年1月4日 下午3:27:41
 */
public interface MongodbFileDao {
  
	/**
	 * 获取二进制文件
	 * 
	 * @param name
	 *            文件名称
	 * @return 文件基本信息
	 */
	public GridFSDBFile findGridFileByName(String name);

	/**
	 * 添加文件数据到数据库
	 * 
	 * @param fileObject
	 *            文件数据实体
	 * @return 文件在数据库中的id
	 */
	public String insert(FileObject fileObject);
	
	
}
