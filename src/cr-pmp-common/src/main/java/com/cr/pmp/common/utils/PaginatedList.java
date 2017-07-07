package com.cr.pmp.common.utils;

import java.util.List;

public abstract interface PaginatedList<T> extends List<T> {
	/**
	 * @描述 : 是否是中间页
	 * @创建者：cr-pmp
	 * @创建时间： 2014-6-30上午10:31:58
	 * 
	 * @return
	 */
	public abstract boolean isMiddlePage();

	/**
	 * @描述 : 是否是末页
	 * @创建者：cr-pmp
	 * @创建时间： 2014-6-30上午10:33:00
	 * 
	 * @return
	 */
	public abstract boolean isLastPage();

	/**
	 * @描述 : 下一页是否可用
	 * @创建者：cr-pmp
	 * @创建时间： 2014-6-30上午10:33:46
	 * 
	 * @return
	 */
	public abstract boolean isNextPageAvailable();

	/**
	 * @描述 : 上一页是否可用
	 * @创建者：cr-pmp
	 * @创建时间： 2014-6-30上午10:34:55
	 * 
	 * @return
	 */
	public abstract boolean isPreviousPageAvailable();

	/**
	 * @描述 : 获取总页数
	 * @创建者：cr-pmp
	 * @创建时间： 2014-6-30上午10:35:35
	 * 
	 * @return
	 */
	public abstract int getPageSize();

	public abstract void setPageSize(int paramInt);

	/**
	 * @描述 : 获取当前页
	 * @创建者：cr-pmp
	 * @创建时间： 2014-6-30上午10:35:50
	 * 
	 * @return
	 */
	public abstract int getIndex();

	public abstract void setIndex(int paramInt);

	/**
	 * @描述 : 获取数据总条数
	 * @创建者：cr-pmp
	 * @创建时间： 2014-6-30上午10:36:08
	 * 
	 * @return
	 */
	public abstract int getTotalItem();

	public abstract void setTotalItem(int paramInt);

	/**
	 * @描述 : 获取总页数
	 * @创建者：cr-pmp
	 * @创建时间： 2014-6-30上午10:36:21
	 * 
	 * @return
	 */
	public abstract int getTotalPage();

	/**
	 * @描述 : 起始条数
	 * @创建者：cr-pmp
	 * @创建时间： 2014-6-30上午10:36:33
	 * 
	 * @return
	 */
	public abstract int getStartRow();

	/**
	 * @描述 : 结束条数
	 * @创建者：cr-pmp
	 * @创建时间： 2014-6-30上午10:36:50
	 * 
	 * @return
	 */
	public abstract int getEndRow();

	/**
	 * @描述 : 下一页
	 * @创建者：cr-pmp
	 * @创建时间： 2014-6-30上午10:37:00
	 * 
	 * @return
	 */
	public abstract int getNextPage();

	/**
	 * @描述 : 上一页
	 * @创建者：cr-pmp
	 * @创建时间： 2014-6-30上午10:37:09
	 * 
	 * @return
	 */
	public abstract int getPreviousPage();

	/**
	 * @描述 : 是否是第一页
	 * @创建者：cr-pmp
	 * @创建时间： 2014-6-30上午10:37:18
	 * 
	 * @return
	 */
	public abstract boolean isFirstPage();
}
