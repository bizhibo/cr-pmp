package com.cr.pmp.model.dept;

import com.cr.pmp.common.base.BaseModel;
/**
 * @描述 : 部门 
 * @创建者：cr-pmp
 * @创建时间： 2016年11月7日上午10:57:32
 *
 */
public class Dept extends BaseModel {

	/** 部门名称 **/
	private String name;
	/** 上级部门ID **/
	private int parentId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

}
