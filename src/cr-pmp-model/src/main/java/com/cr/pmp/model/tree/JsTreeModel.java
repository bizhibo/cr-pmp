package com.cr.pmp.model.tree;

import java.util.List;

/**
 * @描述 : 树
 * @创建者：cr-pmp
 * @创建时间： 2016年11月7日上午11:01:09
 *
 */
public class JsTreeModel {

	/** ID **/
	private String id;
	/** 文本 **/
	private String text;
	/** 图标 **/
	private String icon = "sitemap";
	/** 状态 **/
	private State state = new State();
	/** 下级集合 **/
	private List<JsTreeModel> children;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIcon() {
		return "fa fa-" + icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public List<JsTreeModel> getChildren() {
		return children;
	}

	public void setChildren(List<JsTreeModel> children) {
		this.children = children;
	}

}
