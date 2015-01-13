package com.mongodb.dao;

import java.util.List;

import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.domain.FileObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

/**
 * mongodbDao操作
 * 
 * @author zjl
 * 
 */
public interface MongodbDao {

	/**
	 * 获取数据库集合
	 * 
	 * @return
	 */
	public DBCollection findCollection();

	/**
	 * 添加单条数据到数据库
	 * 
	 * @param object
	 *            数据实体
	 * @return 是否添加成功
	 */
	public boolean insert(DBObject object);

	/**
	 * 添加多条数据到数据库
	 * 
	 * @param object
	 *            数据实体数组
	 * @return 是否添加成功
	 */
	public boolean insert(DBObject[] list);

	/**
	 * 查询数据 object
	 * 查询条件 beginRecord 开始行数 ,
	 * 0,1,2.... rows
	 * 返回行数 sorts 排序条件
	 * @return 是否添加成功
	 */
	public List<DBObject> find(DBObject object, int beginRecord, int rows, DBObject... sorts);

	/**
	 * 添加文件数据到数据库
	 * 
	 * @param gridFSInputFile
	 *            文件数据，例如：图片
	 * @return 文件在数据库中的id
	 */
	public String insert(GridFSInputFile gridFSInputFile);

	/**
	 * 根据文件id查询文件实体
	 * 
	 * @param id
	 *            文件id
	 * @return 文件实体
	 */
	public FileObject findFileById(String id);

	/**
	 * 根据文件名称查询数据实体
	 * 
	 * @param name
	 *            文件名称
	 * @return 文件实体
	 */
	public FileObject findFileByName(String name);

	/**
	 * 根据详细查询条件查询数据实体集合
	 * 
	 * @param object
	 *            详细查询条件
	 * @return 数据实体集合
	 */
	public List<GridFSDBFile> findGridFileByQuery(DBObject object);

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

	/**
	 * 更新单条数据到数据库
	 * 
	 * @param object
	 *            待更新数据
	 * @return 是否更新成功
	 */
	public boolean update(DBObject object);

	/**
	 * 删除单个实体
	 * 
	 * @param object
	 *            待删除实体
	 * @return 是否删除成功
	 */
	public boolean delete(DBObject object);

	/**
	 * 删除单个实体
	 * 
	 * @param object
	 *            待删除实体
	 * @return 是否删除成功
	 */
	public boolean deleteGridFsFile(DBObject object);

	/**
	 * 创建索引
	 * 
	 * @param list
	 *            索引字段集合
	 * @return 创建索引是否成功
	 */
	public boolean createIndex(List<DBObject> list);

	/**
	 * 根据查询条件查询数据总条数
	 * 
	 * @param object
	 *            详细查询条件
	 * @return 返回数据总条数
	 */
	public int searchToCount(DBObject object);

}
