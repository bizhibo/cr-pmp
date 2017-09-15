package com.cr.pmp.model.permissions;

import com.cr.pmp.common.base.BaseModel;

/**
 * @描述 : 权限实体
 * @创建者：cr-pmp
 * @创建时间： 2017年7月4日下午2:52:06
 *
 */
public class Permissions extends BaseModel {
	private String name;
	private String code;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
