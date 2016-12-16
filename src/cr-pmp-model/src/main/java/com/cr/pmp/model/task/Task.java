package com.cr.pmp.model.task;

import java.util.Date;
import java.util.List;

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
	/** 执行人姓名 **/
	private String performerName;
	/** 开始时间 **/
	private Date startDate;
	/** 结束时间 **/
	private Date endDate;
	/** 优先级（1普通 2紧急） **/
	private int priority;
	/** 任务备注 **/
	private String remarks;
	/** 子任务集合 **/
	private List<SubTask> subTasks;

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

	public String getPerformerName() {
		return performerName;
	}

	public void setPerformerName(String performerName) {
		this.performerName = performerName;
	}

	public List<SubTask> getSubTasks() {
		return subTasks;
	}

	public void setSubTasks(List<SubTask> subTasks) {
		this.subTasks = subTasks;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

}
