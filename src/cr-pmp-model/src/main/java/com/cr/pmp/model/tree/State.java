package com.cr.pmp.model.tree;

/**
 * @描述 : 树状态 
 * @创建者：cr-pmp
 * @创建时间： 2016年11月7日上午11:02:11
 *
 */
public class State {

	/** 树打开状态 **/
	private Boolean opened = true;
	/** 树是否可以点击 **/
	private Boolean disabled;
	/** 树是否被选中 **/
	private Boolean selected;

	public Boolean getOpened() {
		return opened;
	}

	public void setOpened(Boolean opened) {
		this.opened = opened;
	}

	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

}
