package com.cr.pmp.model.project;

import com.cr.pmp.common.base.BaseModel;

/**
 * @描述 : 项目成员
 * @创建者：cr-pmp
 * @创建时间： 2016年11月14日下午1:28:44
 *
 */
public class ProjectLeaguer extends BaseModel {

	/** 项目ID **/
	private int pid;
	/** 成员用户名 **/
	private String userName;
	/** 成员部门名称 **/
	private String deptName;
	/** 成员职位 **/
	private String position;
	/** 成员姓名 **/
	private String name;

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
