package com.search.constant;


/**
 * @Filename: secretaryFront→com.qoocc.news.common.constant→NewsConstant.java
 * @Description: 新闻前台常量类
 * @Copyright: Copyright (c)2012
 * @Company: qoocc
 * @author: wzh
 * @version: 1.0
 * @Create at: 2012-11-9上午11:04:04
 * @Modification History:
 * @Date Author Version Description
 * @------------------------------------------------------------------
 * @2012-11-9上午11:04:04 wzh 1.0 1.0 Version
 */
public interface NewsConstant {
	

	/**
	 * 新闻前台全文检索
	 * 
	 * @author zjl
	 * @date 2011-11-2
	 */
	interface SearchIndex {

		/**
		 * 索引新闻数据类型-->事件
		 */
		String EVENT = "event";
		/**
		 * 索引新闻数据类型-->图片
		 */
		String IMAGE = "image";

		/**
		 * 排序方式-->降序
		 */
		String ORDER_DESC = "desc";
		/**
		 * 排序方式-->升序
		 */
		String ORDER_ASC = "asc";
	}

	/**
	 * 搜索页面新闻每页显示条数
	 * 
	 * @author Administrator
	 */
	interface searchPageSize {
		/**
		 * 搜索页面新闻每页显示条数
		 */
		int PAGESIZE = 10;
		/**
		 * 搜索索引库最大条数限制
		 */
		int SEARCH_INDEX_MAX_COUNT = 50;
	}
	
}
