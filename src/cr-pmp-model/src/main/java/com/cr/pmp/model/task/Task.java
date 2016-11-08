package com.cr.pmp.model.task;

import java.util.Date;

import com.cr.pmp.common.base.BaseModel;

/**
 * @描述 : 任务
 * @创建者：cr-pmp
 * @创建时间： 2016年11月7日上午10:58:38
 *
 */
public class Task extends BaseModel {

	/** 所属项目ID **/
	private int pid;
	/** 所属看板ID **/
	private int tbid;
	/** 任务名称 **/
	private String name;
	/** 执行人 **/
	private String performer;
	/** 结束时间 **/
	private Date endDate;
	/** 优先级（1普通 2紧急） **/
	private int priority;
	/** 任务备注 **/
	private String remarks;

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getTbid() {
		return tbid;
	}

	public void setTbid(int tbid) {
		this.tbid = tbid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPerformer() {
		return performer;
	}

	public void setPerformer(String performer) {
		this.performer = performer;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
