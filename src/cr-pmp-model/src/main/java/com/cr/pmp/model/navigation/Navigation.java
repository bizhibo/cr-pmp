package com.cr.pmp.model.navigation;

import java.util.List;

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
	/** 菜单等级（1级 2级 3级） **/
	private int level;
	/** 菜单类型（静态STATIC 动态DYNAMIC） **/
	private String type;
	/** 上级菜单ID **/
	private int parentId;
	/** 菜单url **/
	private String url;
	/** 菜单图标 **/
	private String icon;
	/** 菜单顺序 **/
	private int sequence;
	/** 下级菜单集合 **/
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

	public List<Navigation> getNavList() {
		return navList;
	}

	public void setNavList(List<Navigation> navList) {
		this.navList = navList;
	}

}
