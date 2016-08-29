package com.cr.pmp.common.base;

import java.util.Date;

/**
 * @描述 : base实体
 * @创建者：cr-pmp
 * @创建时间： 2016年8月21日下午9:46:35
 *
 */
public class BaseModel {

	/** 主键 **/
	private int id;
	/** 创建时间 **/
	private Date createTime;
	/** 修改时间 **/
	private Date modifyTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

}
