package com.cr.pmp.controller.dept;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cr.pmp.common.base.BaseController;
import com.cr.pmp.common.result.Result;
import com.cr.pmp.model.dept.Dept;
import com.cr.pmp.service.dept.DeptService;

@Controller
@RequestMapping("/dept")
public class DeptController extends BaseController {

	@Autowired
	private DeptService deptService;

	@RequestMapping("/index")
	public Result index() {
		return new Result("/dept/index");
	}

	@RequestMapping("/pageList")
	public Result queryNavList() {
		Result result = deptService.queryDeptPageList(this.getParams());
		result.setViewName("/dept/deptList");
		return result;
	}

	@RequestMapping("/delDept")
	@ResponseBody
	public String delDept() {
		Result result = deptService.delDept(Integer.valueOf(this
				.getParams("id").toString()));
		return result.toJson();
	}

	@RequestMapping("/updDept")
	@ResponseBody
	public String updDept() {
		Result result = deptService.updDept((Dept) this
				.getParamsObject(Dept.class));
		return result.toJson();
	}

	@RequestMapping("/queryById")
	@ResponseBody
	public String queryById() {
		Result result = deptService.queryById(Integer.valueOf(this.getParams(
				"id").toString()));
		return result.toJson();
	}

	@RequestMapping("/addDept")
	@ResponseBody
	public String addDept() {
		Result result = deptService.addDept((Dept) this
				.getParamsObject(Dept.class));
		return result.toJson();
	}

	@RequestMapping("/getDept")
	@ResponseBody
	public String getDept() {
		Result result = deptService.queryRootNode();
		return result.toJson();
	}
}
