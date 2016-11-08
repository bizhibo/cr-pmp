package com.cr.pmp.dao.project;

import java.util.List;
import java.util.Map;

import com.cr.pmp.model.project.Project;

public interface ProjectDao {

	public List<Project> queryProjectPageList(Map<String, Object> params);

	public Integer queryCount(Map<String, Object> params);

	public Integer addProject(Project project);
}
