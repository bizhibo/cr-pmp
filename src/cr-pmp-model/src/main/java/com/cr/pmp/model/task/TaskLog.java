package com.cr.pmp.model.task;

import com.cr.pmp.common.base.BaseModel;

/**
 * @描述 : 任务日志
 * @创建者：cr-pmp
 * @创建时间： 2016年11月7日上午11:00:24
 *
 */
public class TaskLog extends BaseModel {
	
	/** 所属任务ID **/
	private int tid;
	/** 日志内容 **/
	private String log;

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

}
