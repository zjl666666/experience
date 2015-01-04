/**
 * 
 */
package com.ssh.domain;

import java.io.InputStream;
import java.util.Date;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * 保存文件
 * @author zjl
 * 
 */
public class FileObject {
	/**
	 * id
	 */
	private String id;
	/**
	 * 文件名称
	 */
	private String fileName;
	/**
	 * 文件类型
	 */
	private String contentType;
	/**
	 * md5
	 */
	private String md5;
	/**
	 * 上传日期
	 */
	private Date uploadDate;
	/**
	 * 别名
	 */
	private String aliases;
	/**
	 * 附加信息
	 */
	private DBObject metaData;
	/**
	 * 字节数据
	 */
	private byte[] data;
	/**
	 *输入流数据 
	 */
	private InputStream inputStream;

	
	
	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	

	public String getAliases() {
		return aliases;
	}

	public void setAliases(String aliases) {
		this.aliases = aliases;
	}

	public DBObject getMetaData() {
		return metaData;
	}

	public void setMetaData(DBObject metaData) {
		this.metaData = metaData;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
}
