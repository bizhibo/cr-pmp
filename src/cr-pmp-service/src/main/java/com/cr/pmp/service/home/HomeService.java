package com.cr.pmp.service.home;

import javax.servlet.http.HttpServletRequest;

import com.cr.pmp.common.result.Result;

public interface HomeService {

	public Result index();

	public Result getSoftWare(HttpServletRequest request);
}
