package com.cr.pmp.model.task;

import java.util.Date;

import com.cr.pmp.common.base.BaseModel;

/**
 * @描述 : 子任务
 * @创建者：cr-pmp
 * @创建时间： 2016年11月7日上午10:58:26
 *
 */
public class SubTask extends BaseModel {

	/** 所属主任务ID **/
	private int tid;
	/** 所属看板ID **/
	private int tbid;
	/** 项目ID **/
	private int pid;
	/** 子任务名称 **/
	private String name;
	/** 子任务备注 **/
	private String remarks;
	/** 执行人 **/
	private String performer;
	/** 执行人姓名 **/
	private String performerName;
	/** 开始时间 **/
	private Date startDate;
	/** 结束时间 **/
	private Date endDate;

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public int getTbid() {
		return tbid;
	}

	public void setTbid(int tbid) {
		this.tbid = tbid;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public String getPerformerName() {
		return performerName;
	}

	public void setPerformerName(String performerName) {
		this.performerName = performerName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

}
