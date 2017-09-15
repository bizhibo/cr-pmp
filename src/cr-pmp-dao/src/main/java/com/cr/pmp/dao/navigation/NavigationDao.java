package com.cr.pmp.dao.navigation;

import java.util.List;
import java.util.Map;

import com.cr.pmp.model.navigation.Navigation;

public interface NavigationDao {

	public Integer addNav(Navigation navigation);

	public List<Navigation> queryNavPageList(Map<String, Object> params);

	public Integer queryCount(Map<String, Object> params);

	public Integer delNav(Integer id);
	
	public List<Navigation> queryAllNav();

}
