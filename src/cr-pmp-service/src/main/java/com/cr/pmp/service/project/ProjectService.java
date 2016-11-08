package com.cr.pmp.service.project;

import java.util.Map;

import com.cr.pmp.common.result.Result;
import com.cr.pmp.model.project.Project;

public interface ProjectService {

	public Result queryProjectPageList(Map<String, Object> params);

	public Result addProject(Project project);
}
