package com.cr.pmp.common.utils;

import java.util.ArrayList;

public class PaginatedArrayList<T> extends ArrayList<T> implements
		PaginatedList<T> {
	private static final long serialVersionUID = 2841788433467303352L;
	public static final int PAGESIZE_DEFAULT = 20;
	public static final int PAGEINDEX_DEFAULT = 1;
	private int pageSize;
	private int index;
	private int totalItem;
	private int totalPage;
	private int startRow;
	private int endRow;

	public PaginatedArrayList() {
		repaginate();
	}

	public PaginatedArrayList(int index, int pageSize, int totalItem) {
		this.index = index;
		this.pageSize = pageSize;
		this.totalItem = totalItem;
	}

	public boolean isFirstPage() {
		return this.index <= 1;
	}

	public boolean isMiddlePage() {
		return (!isFirstPage()) && (!isLastPage());
	}

	public boolean isLastPage() {
		return this.index >= this.totalPage;
	}

	public boolean isNextPageAvailable() {
		return !isLastPage();
	}

	public boolean isPreviousPageAvailable() {
		return !isFirstPage();
	}

	public int getNextPage() {
		if (isLastPage()) {
			return this.totalItem;
		}
		return this.index + 1;
	}

	public int getPreviousPage() {
		if (isFirstPage()) {
			return 1;
		}
		return this.index - 1;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getIndex() {
		return this.index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getTotalItem() {
		return this.totalItem;
	}

	public void setTotalItem(int totalItem) {
		this.totalItem = totalItem;
	}

	public int getTotalPage() {
		return this.totalPage;
	}

	public int getStartRow() {
		repaginate();
		return this.startRow;
	}

	public int getEndRow() {
		repaginate();
		return this.endRow;
	}

	/**
	 * @描述 : 分页计算方法
	 * @创建者：liushengsong
	 * @创建时间： 2014-6-30上午10:41:04
	 * 
	 */
	private void repaginate() {
		if (this.pageSize < 1) {
			this.pageSize = 20;
		}
		if (this.index < 1) {
			this.index = 1;
		}
		if (this.totalItem > 0) {
			this.totalPage = this.totalItem / this.pageSize
					+ (this.totalItem % this.pageSize > 0 ? 1 : 0);
			if (this.index > this.totalPage) {
				this.index = this.totalPage;
			}
			this.endRow = this.index * this.pageSize;
			this.startRow = this.endRow - this.pageSize + 1;
			if (this.endRow > this.totalItem) {
				this.endRow = this.totalItem;
			}
		}
	}
}
