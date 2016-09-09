package com.cr.pmp.common.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cr.pmp.common.utils.LogUtils;

/**
 * @描述 控制层
 * @创建者 CR-PMP
 * @创建时间 1/5/2015
 */
public class BaseController {
	@Autowired
	protected HttpServletRequest request;

	@ExceptionHandler({ Exception.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public void exceptionHandler(Exception e) {
		LogUtils.error(e.getMessage(), e);
	}

	public Map<String, Object> getParams() {
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			Map<String, String[]> map = request.getParameterMap();
			Set<String> keys = map.keySet();
			for (String key : keys) {
				String[] values = map.get(key);
				if (values != null && values.length > 1) {
					List<String> valueList = new ArrayList<String>(
							Arrays.asList(values));
					params.put(key, valueList);
				} else if (values != null) {
					params.put(key, values[0]);
				} else {
					params.put(key, "");
				}
			}
		} catch (Exception e) {
			LogUtils.error("获取请求参数异常!", e);
		}
		return params;
	}

	public Object getParams(String name) {
		return this.getParams().get(name);
	}

	public Object getParamsObject(Class<?> clazz) {
		Object object = null;
		try {
			Map<String, Object> params = this.getParams();
			object = clazz.newInstance();
			BeanUtils.copyProperties(object, params);
		} catch (Exception e) {
			LogUtils.error("转换 params to bean异常!", e);
		}
		return object;
	}

	/**
	 * @描述 : 获取request请求的IP地址
	 * @创建者：CR-PMP
	 * @创建时间： 2014-5-4上午11:47:46
	 * @return
	 */
	protected String getRemoteIPs() {
		String ip = request.getHeader("x-forwarded-for");
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (StringUtils.isNotBlank(ip) && ip.contains(",")) {
			ip = ip.substring(0, ip.indexOf(","));
		}
		if (ip != null) {
			return ip.split(":")[0];
		}
		return null;
	}

}
