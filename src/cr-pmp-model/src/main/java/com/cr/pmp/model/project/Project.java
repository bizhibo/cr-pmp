package com.cr.pmp.model.project;

import com.cr.pmp.common.base.BaseModel;

/**
 * @描述 : 项目 
 * @创建者：cr-pmp
 * @创建时间： 2016年11月7日上午10:58:10
 *
 */
public class Project extends BaseModel {

	/** 项目名称 **/
	private String name;
	/** 项目状态（1进行中 2已归档） **/
	private int status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
