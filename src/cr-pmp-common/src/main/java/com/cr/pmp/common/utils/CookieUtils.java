package com.cr.pmp.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

/**
 * @描述 : cookie工具类
 * @创建者：liushengsong
 * @创建时间： 2014-6-11下午2:30:54
 * 
 */
public class CookieUtils {
	/** cookie写入的编码 **/
	private static final String ENC = "utf-8";
	/** cookie写入的域名称 **/
	private String domain;
	/** cookie写入的路径 **/
	private String path;

	/**
	 * @描述 : 获取cookie值的方法
	 * @创建者：liushengsong
	 * @创建时间： 2014-6-11下午2:41:21
	 * 
	 * @param name
	 * @return
	 * @throws RuntimeException
	 */
	public String getCookieValue(String name, HttpServletRequest request)
			throws RuntimeException {
		try {
			Cookie[] cookies = request.getCookies();
			if (cookies != null && cookies.length > 0) {
				for (Cookie cookie : cookies) {
					String cookieName = cookie.getName();
					if (cookieName.equals(name)) {
						if (StringUtils.isNotBlank(cookie.getValue())) {
							return URLDecoder.decode(cookie.getValue(), ENC);
						}
					}
				}
			}
			return null;
		} catch (UnsupportedEncodingException e) {
			LogUtils.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * @描述 : 创建一个cookie
	 * @创建者：liushengsong
	 * @创建时间： 2014-6-11下午2:40:49
	 * 
	 * @param name
	 * @param value
	 * @param maxAge
	 * @throws RuntimeException
	 */
	public void createdCookie(String name, String value, int maxAge,
			HttpServletResponse response) throws RuntimeException {
		try {
			Cookie newCookie = new Cookie(name, URLEncoder.encode(value, ENC));
			newCookie.setMaxAge(maxAge);
			newCookie.setDomain(domain);
			newCookie.setPath(path);
			response.addCookie(newCookie);
		} catch (UnsupportedEncodingException e) {
			LogUtils.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * @描述 : 将一个cookie内容置为空
	 * @创建者：liushengsong
	 * @创建时间： 2014-6-11下午2:41:05
	 * 
	 * @param name
	 */
	public void removeCookie(String name, HttpServletRequest request,
			HttpServletResponse response) {
		String value = this.getCookieValue(name, request);
		if (StringUtils.isNotBlank(value)) {
			this.createdCookie(name, "", 0, response);
		}
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
