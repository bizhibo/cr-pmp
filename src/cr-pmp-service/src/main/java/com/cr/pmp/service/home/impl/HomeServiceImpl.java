package com.cr.pmp.service.home.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cr.pmp.common.result.Result;
import com.cr.pmp.dao.navigation.NavigationDao;
import com.cr.pmp.model.navigation.Navigation;
import com.cr.pmp.service.home.HomeService;

@Service("homeService")
public class HomeServiceImpl implements HomeService {

	@Autowired
	private NavigationDao navigationDao;

	@Override
	public Result index() {
		Result result = new Result();
		List<Navigation> navList = navigationDao.queryRootNav();
		result.addObject("treeNav", navList);
		return result;
	}
}
