package com.cr.pmp.service.project.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cr.pmp.common.result.Result;
import com.cr.pmp.common.utils.LogUtils;
import com.cr.pmp.common.utils.PaginatedArrayList;
import com.cr.pmp.common.utils.PaginatedList;
import com.cr.pmp.dao.project.ProjectDao;
import com.cr.pmp.model.project.Project;
import com.cr.pmp.service.project.ProjectService;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectDao projectDao;

	@Override
	public Result queryProjectPageList(Map<String, Object> params) {
		Result result = new Result();
		try {
			int count = projectDao.queryCount(params);
			if (params.get("page") == null || params.get("page").equals("")
					|| params.get("page").equals("0")) {
				params.put("page", PaginatedArrayList.PAGEINDEX_DEFAULT);
			}
			if (params.get("pageSize") == null
					|| params.get("pageSize").equals("")
					|| params.get("pageSize").equals("0")) {
				params.put("pageSize", PaginatedArrayList.PAGESIZE_DEFAULT);
			}
			PaginatedList<Project> pageList = new PaginatedArrayList<Project>(
					Integer.valueOf(params.get("page").toString()),
					Integer.valueOf(params.get("pageSize").toString()), count);
			params.put("startRow",
					pageList.getStartRow() > 0 ? pageList.getStartRow() - 1
							: pageList.getStartRow());
			List<Project> projectList = projectDao.queryProjectPageList(params);
			pageList.addAll(projectList);
			result.addObject("pageList", pageList);
			result.addObject("params", params);
		} catch (Exception e) {
			LogUtils.error(e.getMessage(), e);
		}
		return result;
	}

	@Override
	public Result addProject(Project project) {
		Result result = new Result();
		try {
			int flag = projectDao.addProject(project);
			if (flag > 0) {
				result.setResultCode(true);
			} else {
				result.setResultCode(false);
			}
		} catch (Exception e) {
			result.setResultCode(false);
			LogUtils.error(e.getMessage(), e);
		}
		return result;
	}

}
