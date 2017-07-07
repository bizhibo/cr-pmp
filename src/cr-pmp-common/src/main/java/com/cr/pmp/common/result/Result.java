package com.cr.pmp.common.result;

import org.springframework.web.servlet.ModelAndView;

import com.cr.pmp.common.utils.JsonUtils;

/**
 * @描述 : 返回的结果集
 * @创建者：cr-pmp
 * @创建时间： 2015年9月2日上午9:40:01
 *
 */
public class Result extends ModelAndView {

	public Result() {
		super();
	};

	public Result(String viewName) {
		super(viewName);
	};

	/**
	 * @描述 :获取结果集json字符串
	 * @创建者：cr-pmp
	 * @创建时间： 2015年9月2日上午9:40:18
	 *
	 * @return
	 */
	public String toJson() {
		return JsonUtils.toJson(this.getModel());
	}

	/**
	 * @描述 : 插入返回信息
	 * @创建者：cr-pmp
	 * @创建时间： 2015年9月2日上午9:55:09
	 *
	 * @param message
	 */
	public void setMessage(String message) {
		this.addObject("message", message);
	}

	/**
	 * @描述 : 插入返回编码
	 * @创建者：cr-pmp
	 * @创建时间： 2015年9月2日上午9:55:24
	 *
	 * @param resultCode
	 */
	public void setResultCode(String resultCode) {
		this.addObject("resultCode", resultCode);
	}

	/**
	 * @描述 : 插入返回编码
	 * @创建者：cr-pmp
	 * @创建时间： 2015年9月2日上午9:55:24
	 *
	 * @param resultCode
	 */
	public void setResultCode(int resultCode) {
		this.addObject("resultCode", resultCode);
	}

	/**
	 * @描述 : 插入返回编码
	 * @创建者：cr-pmp
	 * @创建时间： 2015年9月2日上午9:55:24
	 *
	 * @param resultCode
	 */
	public void setResultCode(boolean resultCode) {
		this.addObject("resultCode", resultCode);
	}

	/**
	 * @描述 : 插入返回异常
	 * @创建者：cr-pmp
	 * @创建时间： 2015年9月2日上午9:55:53
	 *
	 * @param e
	 */
	public void setException(Throwable exception) {
		this.addObject("exception", exception);
	}
}
