package com.cr.pmp.controller.navigation;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cr.pmp.common.base.BaseController;
import com.cr.pmp.common.dict.SystemDict;
import com.cr.pmp.common.result.Result;
import com.cr.pmp.model.navigation.Navigation;
import com.cr.pmp.service.navigation.NavigationService;

@Controller
@RequestMapping("/nav")
public class NavigationController extends BaseController {

	@Autowired
	private NavigationService navigationService;


	@RequestMapping("/add-nav")
	@ResponseBody
	public String addNav() {
		Result result = navigationService.addNav((Navigation) this
				.getParamsObject(Navigation.class));
		return result.toJson();
	}

	@RequestMapping("/page-list")
	public Result queryNavList() {
		Result result = navigationService.queryNavPageList(this.getParams());
		result.setViewName("/nav/navList");
		return result;
	}

	@RequestMapping("/del-nav")
	@ResponseBody
	public String delNav() {
		Result result = navigationService.delNav(Integer.valueOf(this
				.getParams("id").toString()));
		return result.toJson();
	}

	@RequestMapping("/home-nav")
	@ResponseBody
	public String queryAllNav() {
		HttpSession session = request.getSession();
		Result result = navigationService.queryAllNav();
		result.addObject(SystemDict.USERSESSIONKEY,
				session.getAttribute(SystemDict.USERSESSIONKEY));
		result.addObject(SystemDict.USERPERMISSIONSKEY,
				session.getAttribute(SystemDict.USERPERMISSIONSKEY));
		return result.toJson();
	}
}
