package com.cr.pmp.dao.dept;

import java.util.List;
import java.util.Map;

import com.cr.pmp.model.dept.Dept;
import com.cr.pmp.model.tree.JsTreeModel;

public interface DeptDao {

	public Integer addDept(Dept dept);

	public List<Dept> queryDeptPageList(Map<String, Object> params);

	public Integer queryCount(Map<String, Object> params);

	public List<JsTreeModel> queryRootNode();

	public Integer delDept(Integer id);

	public Integer updDept(Dept dept);

	public Dept queryById(Integer id);

}
