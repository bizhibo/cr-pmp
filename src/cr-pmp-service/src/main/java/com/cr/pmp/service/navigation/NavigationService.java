package com.cr.pmp.service.navigation;

import java.util.Map;

import com.cr.pmp.common.result.Result;
import com.cr.pmp.model.navigation.Navigation;

public interface NavigationService {

	public Result addNav(Navigation navigation);

	public Result queryNavPageList(Map<String, Object> params);

	public Result delNav(Integer id);

	public Result queryAllNav();
}
