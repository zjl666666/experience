package com.basis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.search.service.impl.TestIndexServiceImpl;

public class TestCommon {
	
	private static final Logger logger = LoggerFactory.getLogger(TestCommon.class);

	public static void main(String[] args) {
		String str="test";
		String str1="test";
	    logger.debug("测试是否相同的字符串只生成一个"+(str==str1));
	}

}
