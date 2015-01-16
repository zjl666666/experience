package com.mongodb.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mongodb.dao.MongodbFileDao;
import com.mongodb.domain.FileObject;
import com.mongodb.gridfs.GridFSDBFile;

@RunWith(SpringJUnit4ClassRunner.class)
// 指定测试用例的运行器 这里是指定了Junit4
@ContextConfiguration(locations = { "/spring/applicationContext-mongodb.xml" })
public class TestMongodbFileDaoImpl {

	private static final Logger logger = LoggerFactory.getLogger(TestMongodbExampleDaoImpl.class);

	@Resource(name = "mongodbFileDaoImpl")
	private MongodbFileDao mongodbFileDao;

	@Test
	public void testInsert() throws Exception {
		FileObject fileObject = new FileObject();
		File file = new File("d:\\test.js");
		InputStream inputStream = new FileInputStream(file);

		fileObject.setInputStream(inputStream);
		fileObject.setFileName("test.js");
		this.mongodbFileDao.insert(fileObject);
	}

	
	@Test
	public void testFindGridFileByName() throws Exception {
		String name = "test.js";
		GridFSDBFile gridFSDBFile = this.mongodbFileDao.findGridFileByName(name);

		File file = new File("d:\\test1.js");
		InputStream inputStream = gridFSDBFile.getInputStream();
		OutputStream outputStream = new FileOutputStream(file);
		try{
			byte[] strByte = new byte[(int) gridFSDBFile.getLength()];
			while (inputStream.read(strByte) != -1) {
				outputStream.write(strByte);
			}
		}finally{
			inputStream.close();
			outputStream.close();
		}
		

	}

}
