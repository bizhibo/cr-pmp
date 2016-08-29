package com.cr.pmp.service.navigation.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cr.pmp.common.result.Result;
import com.cr.pmp.dao.navigation.NavigationDao;
import com.cr.pmp.model.navigation.Navigation;
import com.cr.pmp.service.navigation.NavigationService;

@Service("navigationService")
public class NavigationServiceImpl implements NavigationService {

	@Autowired
	private NavigationDao navigationDao;

	@Override
	public Result addNav(Navigation navigation) {
		int i = navigationDao.addNav(navigation);
		return null;
	}

}
