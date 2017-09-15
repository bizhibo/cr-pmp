package com.cr.pmp.controller.user;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cr.pmp.common.annotation.NotLogin;
import com.cr.pmp.common.base.BaseController;
import com.cr.pmp.common.dict.SystemDict;
import com.cr.pmp.common.result.Result;
import com.cr.pmp.service.user.UserService;

@Controller
public class LoginController extends BaseController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", method = { RequestMethod.POST })
	@ResponseBody
	@NotLogin
	public String login(String userName, String password) {
		Map<String, Object> params = this.getParams();
		Result result = userService.login(params, request.getSession());
		return result.toJson();
	}

	@RequestMapping("/login-page")
	@NotLogin
	public Result loginPage() {
		return new Result("/login");
	}

	@RequestMapping("/logout")
	@NotLogin
	public void logout(HttpServletResponse response) throws IOException {
		request.getSession().removeAttribute(SystemDict.USERSESSIONKEY);
		response.sendRedirect("/login-page.do");
	}

}
