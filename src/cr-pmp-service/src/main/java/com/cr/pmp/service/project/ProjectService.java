package com.cr.pmp.service.project;

import java.util.Map;

import com.cr.pmp.common.result.Result;
import com.cr.pmp.model.project.Project;
import com.cr.pmp.model.project.ProjectLeaguer;

public interface ProjectService {

	public Result queryProjectPageList(Map<String, Object> params);

	public Result addProject(Project project,String userName);

	public Result queryProjectLeguer(Integer pid);

	public Result addProjectLeguer(ProjectLeaguer projectLeaguer);

	public Result delProjectLeguer(Integer id);
}
