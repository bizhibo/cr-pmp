package com.cr.pmp.service.user.impl;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cr.pmp.common.result.Result;
import com.cr.pmp.common.utils.LogUtils;
import com.cr.pmp.common.utils.SecurityUtils;
import com.cr.pmp.dao.user.UserDao;
import com.cr.pmp.model.user.User;
import com.cr.pmp.service.user.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public Result login(Map<String, Object> params, HttpSession session) {
		Result result = new Result();
		try {
			String md5PW = SecurityUtils.md5(params.get("password").toString());
			params.put("password", md5PW);
			User user = userDao.login(params);
			if (user != null) {
				session.setAttribute("userInfo", user);
				result.setResultCode(true);
				result.setMessage("登陆成功!");
			} else {
				result.setResultCode(false);
				result.setMessage("用户名或密码错误!");
			}
		} catch (Exception e) {
			LogUtils.error("登陆异常", e);
		}
		return result;
	}
}
