package com.cr.pmp.model.dept;

import com.cr.pmp.common.base.BaseModel;

public class Dept extends BaseModel {

	private String name;
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