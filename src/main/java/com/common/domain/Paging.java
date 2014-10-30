package com.common.domain;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class Paging<T> {

	/**
	 * 升序排列
	 */
	public static final String ASC = "asc";
	/**
	 * 降序排列
	 */
	public static final String DESC = "desc";
	/**
	 * 第几页
	 */
	protected int currentPageNo = 1;
	
	/**
	 * 每页的个数
	 */
	protected int pageSize = 10;
	/**
	 * 排序字段
	 */
	protected String orderBy = null;
	/**
	 * 排序的方式
	 */
	protected String order = ASC;
	/**
	 * 是否自动执行查询总记录数
	 */
	protected boolean autoCount = true;

	/**
	 * 返回的结果
	 */
	protected List<T> resultList = null;
	/**
	 * 返回的总条数
	 */
	protected int totalCount = -1;
	/**
	 * 总页数
	 */
	private int totalPages;
	
    /**
     * 默认构造函数
     */
	public Paging() {
		super();
	}
    /**
     * 初始化每页个数的构造函数
     * @param pageSize  每页的个数
     */
	public Paging(final int pageSize) {
		setPageSize(pageSize);
	}
    /**
     * 初始化每页个数的构造函数，初始化是否自动查询总个数
     * @param pageSize  每页的个数
     * @param autoCount 是否自动查询总页数
     */
	public Paging(final int pageSize, final boolean autoCount) {
		setPageSize(pageSize);
		this.autoCount = autoCount;
	}

	//查询参数函数

	/**
	 * 获得当前页的页号,序号从1开始,默认为1.
	 */
	public int getCurrentPageNo() {
		return currentPageNo;
	}

	/**
	 * 设置当前页的页号,序号从1开始,低于1时自动调整为1.
	 * @param  页码
	 */
	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;

		if (currentPageNo < 1) {
			this.currentPageNo = 1;
		}
	}
	/**
	 * 获得每页的记录数量,默认为10.
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 设置每页的记录数量,超出MIN_PAGESIZE与MAX_PAGESIZE范围时会自动调整.
	 * @param pageSize  每页的个数
	 */
	public void setPageSize(final int pageSize) {
		this.pageSize = pageSize;

		/*if (pageSize <= MIN_PAGESIZE) {
			this.pageSize = MIN_PAGESIZE;
		}
		if (pageSize >= MAX_PAGESIZE) {
			this.pageSize = MAX_PAGESIZE;
		}*/
	}

	/**
	* 根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置,序号从0开始.
	*/
	public int getFirst() {
		return ((currentPageNo - 1) * pageSize);
	}

	/**
	 * 获得排序字段,无默认值.多个排序字段时用','分隔,仅在Criterion查询时有效.
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * 设置排序字段.多个排序字段时用','分隔.仅在Criterion查询时有效.
	 * @param orderBy  排序的字段
	 */
	public void setOrderBy(final String orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * 是否已设置排序字段,仅在Criterion查询时有效.
	 */
	public boolean isOrderBySetted() {
		return StringUtils.isNotBlank(orderBy);
	}

	/**
	 * 获得排序方向,默认为asc,仅在Criterion查询时有效.
	 * 
	 * @param order 可选值为desc或asc,多个排序字段时用','分隔.
	 */
	public String getOrder() {
		return order;
	}

	/**
	 * 设置排序方式向,仅在Criterion查询时有效.
	 * 
	 * @param order 可选值为desc或asc,多个排序字段时用','分隔.
	 */
	public void setOrder(final String order) {
		//检查order字符串的合法值
		String[] orders = StringUtils.split(order, ',');
		for (String orderStr : orders) {
			if (!StringUtils.equalsIgnoreCase(DESC, orderStr) && !StringUtils.equalsIgnoreCase(ASC, orderStr))
				throw new IllegalArgumentException("排序方向" + orderStr + "不是合法值");
		}

		this.order = order.toLowerCase();
	}

	/**
	 * 取得分页参数的组合字符串.
	 * 将多个分页参数组合成一个字符串方便在页面上的传递,格式为pageNo|orderBy|order.
	 */
	public String getPageRequest() {
		return getCurrentPageNo() + "|" + StringUtils.defaultString(getOrderBy()) + "|" + getOrder();
	}

	/**
	 * 设置分页参数的组合字符串.
	 * 将多个分页参数组合成一个字符串方便在页面上的传递,格式为pageNo|orderBy|order.
	 * @param pageRequest  每页的结果集
	 */
	public void setPageRequest(final String pageRequest) {

		if (StringUtils.isBlank(pageRequest))
			return;
		String[] params = StringUtils.splitPreserveAllTokens(pageRequest, '|');
		if (StringUtils.isNumeric(params[0])) {
			setCurrentPageNo(Integer.valueOf(params[0]));
		}

		if (StringUtils.isNotBlank(params[1])) {
			setOrderBy(params[1]);
		}

		if (StringUtils.isNotBlank(params[2])) {
			setOrder(params[2]);
		}
	}

	/**
	 * 查询对象时是否自动另外执行count查询获取总记录数,默认为false,仅在Criterion查询时有效.
	 */
	public boolean isAutoCount() {
		return autoCount;
	}

	/**
	 * 查询对象时是否自动另外执行count查询获取总记录数,仅在Criterion查询时有效.、
	 * @param  是否自动执行查询总页数
	 */
	public void setAutoCount(final boolean autoCount) {
		this.autoCount = autoCount;
	}

	// 查询结果函数

	/**
	 * 取得页内的记录列表.
	 */
	public List<T> getResultList() {
		if (resultList == null)
			return Collections.emptyList();
		return resultList;
	}

	/**
	 * 设置结果集
	 * @param result  结果集
	 */
	public void setResultList(final List<T> result) {
		this.resultList = result;
	}

	/**
	 * 取得总记录数,默认值为-1.
	 */
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * 总条数
	 * @param totalCount 总条数
	 */
	public void setTotalCount(final int totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * 根据pageSize与totalCount计算总页数,默认值为-1.
	 */
	public int getTotalPages() {
		if (totalCount < 0)
			return -1;

		int count = totalCount / pageSize;
		if (totalCount % pageSize > 0) {
			count++;
		}
		return count;
	}


	/**
	 * 是否还有下一页.
	 */
	public boolean isHasNext() {
		return (currentPageNo + 1 <= getTotalPages());
	}

	/**
	 * 取得下页的页号,序号从1开始.
	 */
	public int getNextPage() {
		if (isHasNext())
			return currentPageNo + 1;
		else
			return currentPageNo;
	}

	/**
	 * 是否还有上一页. 
	 */
	public boolean isHasPre() {
		return (currentPageNo - 1 >= 1);
	}

	/**
	 * 取得上页的页号,序号从1开始.
	 */
	public int getPrePage() {
		if (isHasPre())
			return currentPageNo - 1;
		else
			return currentPageNo;
	}
    
	/**
	 * 取得反转的排序方向.
	 * 如果有以','分隔的多个排序方向,逐一进行反转.
	 */
	public String getInverseOrder() {
		String[] orders = StringUtils.split(order, ',');

		for (int i = 0; i < orders.length; i++) {
			if (DESC.equals(orders[i])) {
				orders[i] = ASC;
			} else {
				orders[i] = DESC;
			}
		}
		return StringUtils.join(orders);
	}

}
