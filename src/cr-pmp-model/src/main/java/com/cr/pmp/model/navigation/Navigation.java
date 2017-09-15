package com.cr.pmp.model.navigation;

import com.cr.pmp.common.base.BaseModel;

/**
 * @描述 : 菜单
 * @创建者：cr-pmp
 * @创建时间： 2016年11月7日上午10:57:50
 *
 */
public class Navigation extends BaseModel {

	/** 菜单名称 **/
	private String name;
	/** 菜单url **/
	private String url;
	/** 菜单图标 **/
	private String icon;
	/** 菜单顺序 **/
	private int sequence;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

}
