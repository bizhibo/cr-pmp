package com.cr.pmp.service.navigation.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cr.pmp.common.result.Result;
import com.cr.pmp.common.utils.JsonUtils;
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
			result.addObject("params", params);
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
			Integer flag = navigationDao.delNav(id);
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
	public Result queryAllNav() {
		Result result = new Result();
		try {
			List<List<Navigation>> lists = new ArrayList<List<Navigation>>();
			List<Navigation> list = navigationDao.queryAllNav();
			int j = list.size() / 3 + (list.size() % 3 > 0 ? 1 : 0);
			int k = 0;
			for (int i = 1; i <= j; i++) {
				if (i == j) {
					lists.add(list.subList(k, list.size()));
				} else {
					lists.add(list.subList(k, k + 3));
					k = k + 3;
				}
			}
			result.addObject("lists", lists);
		} catch (Exception e) {
			LogUtils.error(e.getMessage(), e);
		}
		return result;
	}
}
