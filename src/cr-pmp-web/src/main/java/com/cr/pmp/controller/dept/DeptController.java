package com.cr.pmp.controller.dept;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cr.pmp.common.base.BaseController;
import com.cr.pmp.common.result.Result;
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

	@RequestMapping("/getDept")
	@ResponseBody
	public String getDept() {
		Result result = deptService.queryRootNode();
		return result.toJson();
	}
}
