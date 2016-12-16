package com.cr.pmp.dao.project;

import java.util.List;
import java.util.Map;

import com.cr.pmp.model.project.Project;
import com.cr.pmp.model.project.ProjectLeaguer;

public interface ProjectDao {

	public List<Project> queryProjectPageList(Map<String, Object> params);

	public Integer queryCount(Map<String, Object> params);

	public Integer addProject(Project project);

	public List<ProjectLeaguer> queryProjectLeguer(Integer pid);

	public Integer addProjectLeguer(ProjectLeaguer projectLeaguer);

	public Integer delProjectLeguer(Integer id);

	public ProjectLeaguer queryProjectLeguerByUser(Map<String, Object> params);
}
