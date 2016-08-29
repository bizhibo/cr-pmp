package com.cr.pmp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cr.pmp.common.base.BaseController;
import com.cr.pmp.common.result.Result;
import com.cr.pmp.service.user.UserService;

@Controller
@RequestMapping("/test")
public class TestController extends BaseController {

	@Autowired
	private UserService userService;

	@RequestMapping("/index")
	public Result test() {
		Result r = new Result();
		r.setViewName("/index");
		return r;
	}


}
