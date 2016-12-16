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
	/** 主任务总数 **/
	private int masterTaskCount;
	/** 子任务总数 **/
	private int subTaskCount;
	/** 项目名称 **/
	private int leaguerCount;
	/** 全部任务总数 **/
	private int taskCount;

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

	public int getMasterTaskCount() {
		return masterTaskCount;
	}

	public void setMasterTaskCount(int masterTaskCount) {
		this.masterTaskCount = masterTaskCount;
	}

	public int getSubTaskCount() {
		return subTaskCount;
	}

	public void setSubTaskCount(int subTaskCount) {
		this.subTaskCount = subTaskCount;
	}

	public int getLeaguerCount() {
		return leaguerCount;
	}

	public void setLeaguerCount(int leaguerCount) {
		this.leaguerCount = leaguerCount;
	}

	public int getTaskCount() {
		taskCount = masterTaskCount + subTaskCount;
		return taskCount;
	}

}
