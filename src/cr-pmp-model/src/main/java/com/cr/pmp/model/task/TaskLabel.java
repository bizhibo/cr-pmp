package com.cr.pmp.model.task;

import com.cr.pmp.common.base.BaseModel;
/**
 * @描述 : 任务标签
 * @创建者：cr-pmp
 * @创建时间： 2016年11月7日上午10:59:46
 *
 */
public class TaskLabel extends BaseModel {

	/** 所属任务ID **/
	private int tid;
	/** 标签名称 **/
	private String name;

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
