package com.search.service.impl;

import java.util.Calendar;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.search.bean.NewsIndex;
import com.search.bean.NewsIndexList;
import com.search.bean.SearchIndex;

/**
 * 
 * @Description: 测试索引相关操作
 * @author zjl
 * @date 2014年9月11日 下午3:57:13
 */
public class TestIndexServiceImpl {

	private static final Logger logger = LoggerFactory.getLogger(TestIndexServiceImpl.class);
	
	@Test
	public void TestAdd(){
		IndexServiceImpl  service =new IndexServiceImpl();
		
		NewsIndex index=new NewsIndex();
		index.setId("111");
		index.setTitle("新疆轮台暴恐案围剿暴徒视频曝光90");
		service.add(index);
	}
	
	
	@Test
	public void TestSearch(){
		IndexServiceImpl  service =new IndexServiceImpl();
		SearchIndex searchIndex=new SearchIndex();
		searchIndex.setKeyword("90");
		searchIndex.setBeginRecord(0);
		searchIndex.setRows(50);
		NewsIndexList  list=service.search(searchIndex);
		for(NewsIndex index:list){
			System.out.println(index.getTitle());
			logger.debug("title=="+index.getTitle());
		}
	}
}
