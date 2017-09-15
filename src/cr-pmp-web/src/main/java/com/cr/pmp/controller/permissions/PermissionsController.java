package com.cr.pmp.controller.permissions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cr.pmp.common.base.BaseController;
import com.cr.pmp.common.result.Result;
import com.cr.pmp.model.permissions.Permissions;
import com.cr.pmp.service.permissions.PermissionsService;

@Controller
@RequestMapping("/permissions")
public class PermissionsController extends BaseController {

	@Autowired
	private PermissionsService permissionsService;

	@RequestMapping("/index")
	public Result index() {
		return new Result("/permissions/index");
	}

	@RequestMapping("/page-list")
	public Result queryPermissionsList() {
		Result result = permissionsService.queryPermissionsPageList(this
				.getParams());
		result.setViewName("/permissions/permissionsList");
		return result;
	}

	@RequestMapping("/add-permissions")
	@ResponseBody
	public String addPermissions() {
		Result result = permissionsService.addPermissions((Permissions) this
				.getParamsObject(Permissions.class));
		return result.toJson();
	}

	@RequestMapping("/del-permissions")
	@ResponseBody
	public String delPermissions() {
		Result result = permissionsService.delPermissions(Integer
				.valueOf(this.getParams("id").toString()));
		return result.toJson();
	}

	@RequestMapping("/check-permissions-exist")
	@ResponseBody
	public String checkPermissionsExist() {
		Result result = permissionsService.checkExistByCode(this.getParams(
				"code").toString());
		return result.toJson();
	}

	@RequestMapping("/query-by-id")
	@ResponseBody
	public String queryById() {
		Result result = permissionsService.queryById(Integer.valueOf(this
				.getParams("id").toString()));
		return result.toJson();
	}

	@RequestMapping("/upd-permissions")
	@ResponseBody
	public String updPermissions() {
		Result result = permissionsService.updPermissions((Permissions) this
				.getParamsObject(Permissions.class));
		return result.toJson();
	}
	

}
