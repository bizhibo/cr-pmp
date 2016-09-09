package com.cr.pmp.service.dept;

import java.util.Map;

import com.cr.pmp.common.result.Result;
import com.cr.pmp.model.dept.Dept;

public interface DeptService {

	public Result queryRootNode();

	public Result addDept(Dept dept);

	public Result queryDeptPageList(Map<String, Object> params);

	public Result delDept(Integer id);

	public Result updDept(Dept dept);

	public Result queryById(Integer id);
}
