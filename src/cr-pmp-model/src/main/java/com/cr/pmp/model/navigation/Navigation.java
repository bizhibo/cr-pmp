package com.cr.pmp.model.navigation;

import java.util.List;

import com.cr.pmp.common.base.BaseModel;

public class Navigation extends BaseModel {

	private String name;
	private int level;
	private String type;
	private int parentId;
	private String url;
	private List<Navigation> navList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Navigation> getNavList() {
		return navList;
	}

	public void setNavList(List<Navigation> navList) {
		this.navList = navList;
	}

}
