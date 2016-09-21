package com.cr.pmp.service.dept.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cr.pmp.common.result.Result;
import com.cr.pmp.common.utils.LogUtils;
import com.cr.pmp.common.utils.PaginatedArrayList;
import com.cr.pmp.common.utils.PaginatedList;
import com.cr.pmp.dao.dept.DeptDao;
import com.cr.pmp.model.dept.Dept;
import com.cr.pmp.model.tree.JsTreeModel;
import com.cr.pmp.service.dept.DeptService;

@Service("deptService")
public class DeptServiceImpl implements DeptService {

	@Autowired
	private DeptDao deptDao;

	@Override
	public Result delDept(Integer id) {
		Result result = new Result();
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("parentId", id);
			int count = deptDao.queryCount(params);
			if (count > 0) {
				result.setResultCode("EXISTNODE");
			} else {
				Integer flag = deptDao.delDept(id);
				if (flag > 0) {
					result.setResultCode(true);
				} else {
					result.setResultCode(false);
				}
			}
		} catch (Exception e) {
			result.setResultCode(false);
			LogUtils.error(e.getMessage(), e);
		}
		return result;
	}

	@Override
	public Result queryById(Integer id) {
		Result result = new Result();
		try {
			Dept dept = deptDao.queryById(id);
			result.addObject("dept", dept);
		} catch (Exception e) {
			LogUtils.error(e.getMessage(), e);
		}
		return result;
	}

	@Override
	public Result updDept(Dept dept) {
		Result result = new Result();
		try {
			Integer flag = deptDao.updDept(dept);
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

	@Override
	public Result addDept(Dept dept) {
		Result result = new Result();
		try {
			Integer flag = deptDao.addDept(dept);
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

	@Override
	public Result queryDeptPageList(Map<String, Object> params) {
		Result result = new Result();
		try {
			int count = deptDao.queryCount(params);
			if (params.get("page") == null || params.get("page").equals("")
					|| params.get("page").equals("0")) {
				params.put("page", PaginatedArrayList.PAGEINDEX_DEFAULT);
			}
			if (params.get("pageSize") == null
					|| params.get("pageSize").equals("")
					|| params.get("pageSize").equals("0")) {
				params.put("pageSize", PaginatedArrayList.PAGESIZE_DEFAULT);
			}
			PaginatedList<Dept> pageList = new PaginatedArrayList<Dept>(
					Integer.valueOf(params.get("page").toString()),
					Integer.valueOf(params.get("pageSize").toString()), count);
			params.put("startRow",
					pageList.getStartRow() > 0 ? pageList.getStartRow() - 1
							: pageList.getStartRow());
			List<Dept> deptList = deptDao.queryDeptPageList(params);
			pageList.addAll(deptList);
			result.addObject("pageList", pageList);
			result.addObject("params", params);
		} catch (Exception e) {
			LogUtils.error(e.getMessage(), e);
		}
		return result;
	}

	@Override
	public Result queryRootNode() {
		Result result = new Result();
		try {
			List<JsTreeModel> treeList = deptDao.queryRootNode();
			result.addObject("treeData", treeList);
		} catch (Exception e) {
			LogUtils.error(e.getMessage(), e);
		}
		return result;
	}

}
