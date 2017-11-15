package com.cr.pmp.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cr.pmp.common.annotation.PermissionsCode;
import com.cr.pmp.common.dict.SystemDict;
import com.cr.pmp.common.utils.LogUtils;
import com.cr.pmp.model.permissions.UserPermissions;

public class PermissionsInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) {
		try {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			PermissionsCode permissionsCode = handlerMethod.getMethod()
					.getAnnotation(PermissionsCode.class);
			if (permissionsCode == null) {
				permissionsCode = handlerMethod.getMethod().getDeclaringClass()
						.getAnnotation(PermissionsCode.class);
			}
			if (permissionsCode != null) {
				String code = permissionsCode.value();
				Map<String, UserPermissions> ujMap = (Map<String, UserPermissions>) request
						.getSession().getAttribute(
								SystemDict.USERPERMISSIONSKEY);
				UserPermissions userPermissions = ujMap.get(code);
				if (userPermissions != null) {
					return true;
				} else {
					return false;
				}
			} else {
				return true;
			}
		} catch (Exception e) {
			LogUtils.error("权限拦截发生异常", e);
			return false;
		}
	}

	private void redirectLoginPage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		/** ajax **/
		if (request.getHeader("X-Requested-With") != null
				&& request.getHeader("X-Requested-With").equals(
						"XMLHttpRequest")) {
			printJson(response, "No permissions Request");
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
