package com.cr.pmp.interceptor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cr.pmp.common.annotation.NotLogin;
import com.cr.pmp.common.dict.SystemDict;
import com.cr.pmp.common.utils.LogUtils;
import com.cr.pmp.model.user.User;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) {
		try {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			NotLogin notLogin = handlerMethod.getMethod().getAnnotation(
					NotLogin.class);
			if (notLogin == null) {
				notLogin = handlerMethod.getMethod().getDeclaringClass()
						.getAnnotation(NotLogin.class);
			}
			if (notLogin != null) {
				return true;// 存在NotLogin的注解，验证直接通过
			} else {
				User user = (User) request.getSession().getAttribute(
						SystemDict.USERSESSIONKEY);
				if (user != null) {
					return true;
				} else {
					redirectLoginPage(request, response);
					return false;
				}

			}
		} catch (Exception e) {
			LogUtils.error("登陆拦截发生异常", e);
			return false;
		}
	}

	private void redirectLoginPage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		/** ajax **/
		if (request.getHeader("X-Requested-With") != null
				&& request.getHeader("X-Requested-With").equals(
						"XMLHttpRequest")) {
			printJson(response, "NotLogin");
			return;
		}
		/** 非ajax **/
		response.sendRedirect("/login-page.do");
	}

	private void printJson(HttpServletResponse response, String str) {
		PrintWriter printWriter = null;
		try {
			response.setContentType("application/json; charset=UTF-8");
			printWriter = response.getWriter();
			printWriter.write(str);
		} catch (IOException e) {
			LogUtils.error(e.getMessage(), e);
		} finally {
			if (printWriter != null) {
				printWriter.flush();
				printWriter.close();
			}
		}
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) {

	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception e) {

	}

}
