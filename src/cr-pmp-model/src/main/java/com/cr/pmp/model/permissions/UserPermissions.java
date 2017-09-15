package com.cr.pmp.model.permissions;

import com.cr.pmp.common.base.BaseModel;

public class UserPermissions extends BaseModel {

	private String userName;
	private int jid;
	private String code;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getJid() {
		return jid;
	}

	public void setJid(int jid) {
		this.jid = jid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
