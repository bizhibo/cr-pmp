package com.cr.pmp.model.task;

import java.util.List;

import com.cr.pmp.common.base.BaseModel;

/**
 * @描述 : 任务看板
 * @创建者：cr-pmp
 * @创建时间： 2016年11月7日上午10:58:52
 *
 */
public class TaskBoard extends BaseModel {
	
	/** 所属项目ID **/
	private int pid;
	/** 看板名称 **/
	private String name;
	/** 看板任务集合 **/
	private List<Task> tasks;

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

}
