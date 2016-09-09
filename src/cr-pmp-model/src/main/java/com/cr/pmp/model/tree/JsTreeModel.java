package com.cr.pmp.model.tree;

import java.util.List;

public class JsTreeModel {

	private String id;
	private String text;
	private String icon = "sitemap";
	private State state = new State();
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
