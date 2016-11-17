package com.cr.pmp.controller.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cr.pmp.common.base.BaseController;
import com.cr.pmp.common.result.Result;
import com.cr.pmp.service.home.HomeService;

@Controller
@RequestMapping("/home")
public class HomeController extends BaseController {

	@Autowired
	private HomeService homeService;

	@RequestMapping("/index")
	public Result index() {
		Result result = homeService.index();
		result.addObject("userInfo", this.getLoginUserInfo());
		result.setViewName("/index");
		return result;
	}
}
