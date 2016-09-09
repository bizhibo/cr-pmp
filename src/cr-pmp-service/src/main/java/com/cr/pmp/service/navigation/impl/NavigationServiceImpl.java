package com.cr.pmp.service.navigation.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cr.pmp.common.result.Result;
import com.cr.pmp.common.utils.LogUtils;
import com.cr.pmp.common.utils.PaginatedArrayList;
import com.cr.pmp.common.utils.PaginatedList;
import com.cr.pmp.dao.navigation.NavigationDao;
import com.cr.pmp.model.navigation.Navigation;
import com.cr.pmp.service.navigation.NavigationService;

@Service("navigationService")
public class NavigationServiceImpl implements NavigationService {

	@Autowired
	private NavigationDao navigationDao;

	@Override
	public Result queryNavPageList(Map<String, Object> params) {
		Result result = new Result();
		try {
			int count = navigationDao.queryCount(params);
			if (params.get("page") == null || params.get("page").equals("")
					|| params.get("page").equals("0")) {
				params.put("page", PaginatedArrayList.PAGEINDEX_DEFAULT);
			}
			if (params.get("pageSize") == null
					|| params.get("pageSize").equals("")
					|| params.get("pageSize").equals("0")) {
				params.put("pageSize", PaginatedArrayList.PAGESIZE_DEFAULT);
			}
			PaginatedList<Navigation> pageList = new PaginatedArrayList<Navigation>(
					Integer.valueOf(params.get("page").toString()),
					Integer.valueOf(params.get("pageSize").toString()), count);
			params.put("startRow",
					pageList.getStartRow() > 0 ? pageList.getStartRow() - 1
							: pageList.getStartRow());
			List<Navigation> navList = navigationDao.queryNavPageList(params);
			pageList.addAll(navList);
			result.addObject("pageList", pageList);
		} catch (Exception e) {
			LogUtils.error(e.getMessage(), e);
		}
		return result;
	}

	@Override
	public Result queryListByLevel(Integer level) {
		Result result = new Result();
		try {
			List<Navigation> list = navigationDao.queryListByLevel(level);
			result.addObject("navList", list);
		} catch (Exception e) {
			LogUtils.error(e.getMessage(), e);
		}
		return result;
	}

	@Override
	public Result addNav(Navigation navigation) {
		Result result = new Result();
		try {
			int flag = navigationDao.addNav(navigation);
			if (flag > 0) {
				result.setResultCode(true);
			} else {
				result.setResultCode(false);
			}
		} catch (Exception e) {
			result.setResultCode(false);
			LogUtils.error(e.getMessage(), e);
		}
		return result;
	}

	@Override
	public Result delNav(Integer id) {
		Result result = new Result();
		try {
			int count = navigationDao.queryCountByPid(id);
			if (count > 0) {
				result.setResultCode("EXISTNODE");
			} else {
				Integer flag = navigationDao.delNav(id);
				if (flag > 0) {
					result.setResultCode(true);
				} else {
					result.setResultCode(false);
				}
			}
		} catch (Exception e) {
			result.setResultCode(false);
			LogUtils.error(e.getMessage(), e);
		}
		return result;
	}

}
