/**
 * 
 */
package com.search.bean;

import java.util.ArrayList;
import java.util.Date;

/**
 * 查询索引返回数据列表
 * @author zjl
 * 
 */
public class NewsIndexList extends ArrayList<NewsIndex> {
	/**
	 * 总条数
	 */
	private long allNum;
	
	/**
	 * 最近更新的时间
	 */
	private Date modifyTime=new Date();
	
	

	/**
	 * @return the modifyTime
	 */
	public Date getModifyTime() {
		return modifyTime;
	}

	/**
	 * @param modifyTime the modifyTime to set
	 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	/**
	 * 获取总条数
	 * 
	 * @return
	 */
	public long getAllNum() {
		return allNum;
	}

	public void setAllNum(long allNum) {
		this.allNum = allNum;
	}
}
