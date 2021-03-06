package com.cr.pmp.controller.user;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cr.pmp.common.base.BaseController;
import com.cr.pmp.common.dict.FilePathDict;
import com.cr.pmp.common.result.Result;
import com.cr.pmp.common.utils.DateUtils;
import com.cr.pmp.common.utils.JsonUtils;
import com.cr.pmp.common.utils.SecurityUtils;
import com.cr.pmp.common.utils.UUIDUtils;
import com.cr.pmp.model.permissions.UserPermissions;
import com.cr.pmp.model.user.User;
import com.cr.pmp.service.user.UserService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;


	@RequestMapping("/page-list")
	public Result queryUserList() {
		Result result = userService.queryUserPageList(this.getParams());
		result.setViewName("/user/userList");
		return result;
	}

	@RequestMapping(value = "/get-all-user", method = { RequestMethod.POST })
	@ResponseBody
	public String getAllUser() {
		Result result = userService.queryAllUser();
		return result.toJson();
	}

	@RequestMapping("/go-add-page")
	public Result goAddPage() {
		Result result = new Result();
		result.setViewName("/user/addUser");
		return result;
	}

	@RequestMapping("/add-user")
	@ResponseBody
	public String addUser(MultipartFile addFile, String userName,
			String password, String name, String birthday, String sex,
			String deptId, String email, String phone, String position)
			throws Exception {
		StringBuffer fileName = new StringBuffer("default-avatar.jpg");
		if (addFile.getSize() > 0) {
			String path = request.getSession().getServletContext()
					.getRealPath(FilePathDict.USERFILEPATH);
			fileName = new StringBuffer(UUIDUtils.upperUUID()).append(addFile
					.getOriginalFilename());
			File targetFile = new File(path, fileName.toString());
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			addFile.transferTo(targetFile);
		}
		User user = new User();
		user.setBirthday(DateUtils.parseDate(birthday));
		user.setDeptId(Integer.valueOf(deptId));
		user.setEmail(email);
		user.setName(name);
		user.setPassword(SecurityUtils.md5(password));
		user.setPhone(phone);
		user.setPosition(position);
		user.setUserName(userName);
		user.setSex(sex);
		user.setPortraitUrl(FilePathDict.USERFILEPATH + fileName);
		Result result = userService.addUser(user);
		return result.toJson();
	}

	@RequestMapping("/user-Info")
	@ResponseBody
	public String getUserInfo() {
		Result result = userService.queryUserInfo(this.getParams("userName")
				.toString());
		return result.toJson();
	}

	@RequestMapping("/del-user")
	@ResponseBody
	public String delUser() {
		Result result = userService.delUser(this.getParams("userName")
				.toString());
		return result.toJson();
	}

	@RequestMapping("/check-user-exist")
	@ResponseBody
	public String checkUserExist() {
		Result result = userService.checkUserExist(this.getParams());
		return result.toJson();
	}

	@RequestMapping("/check-password")
	@ResponseBody
	public String checkPassword() {
		Result result = userService.checkPassword(this.getParams());
		return result.toJson();
	}

	@RequestMapping("/upd-user")
	@ResponseBody
	public String updUser() {
		Result result = userService.updUser((User) this
				.getParamsObject(User.class));
		return result.toJson();
	}


	@RequestMapping("/get-user-permissions")
	@ResponseBody
	public String getUserPermissions() {
		Result result = userService.queryUserPermissions(this.getParams(
				"userName").toString());
		return result.toJson();
	}

	@RequestMapping("/add-user-permissions")
	@ResponseBody
	public String addUserPermissions() {
		System.out.println(this.getParams("ujList"));
		List<UserPermissions> ujList = JsonUtils.fromJson(
				this.getParams("ujList").toString(), List.class,
				UserPermissions.class);
		Result result = userService.addUserPermissions(ujList,
				this.getParams("userName").toString());
		return result.toJson();
	}
}
