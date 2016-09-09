package com.cr.pmp.service.dept;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cr.pmp.common.result.Result;
import com.cr.pmp.dao.dept.DeptDao;
import com.cr.pmp.model.tree.JsTreeModel;

@Service("deptService")
public class DeptServiceImpl implements DeptService {

	@Autowired
	private DeptDao deptDao;

	@Override
	public Result queryRootNode() {
		Result result = new Result();
		List<JsTreeModel> treeList = deptDao.queryRootNode();
		result.addObject("treeData", treeList);
		return result;
	}

}
