package com.cr.pmp.web.navigation;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cr.pmp.common.base.BaseController;
import com.cr.pmp.common.result.Result;
import com.cr.pmp.common.utils.PaginatedArrayList;
import com.cr.pmp.common.utils.PaginatedList;
import com.cr.pmp.model.navigation.Navigation;
import com.cr.pmp.service.navigation.NavigationService;

@Controller
@RequestMapping("/nav")
public class NavigationController extends BaseController {

	@Autowired
	private NavigationService navigationService;

	@RequestMapping("/index")
	public Result index() {
		Result r = new Result("/nav/index");
		return r;
	}

	@RequestMapping("/addNav")
	@ResponseBody
	public String addNav() {
		navigationService.addNav((Navigation) this
				.getParamsObject(Navigation.class));
		return null;
	}

	@RequestMapping("/pageList")
	public Result queryNavList(Integer page) {
		if (page == null) {
			page = 1;
		}
		Map<String, String> m = new HashMap<String, String>();
		m.put("id", "id");
		m.put("name", "id");
		m.put("sex", "id");
		m.put("age", "id");
		m.put("aa", "id");
		Map<String, String> m1 = new HashMap<String, String>();
		m1.put("id", "id");
		m1.put("name", "id");
		m1.put("sex", "id");
		m1.put("age", "id");
		m1.put("aa", "id");
		Map<String, String> m2 = new HashMap<String, String>();
		m2.put("id", "id");
		m2.put("name", "id");
		m2.put("sex", "id");
		m2.put("age", "id");
		m2.put("aa", "id");
		PaginatedList<Map<String, String>> l = new PaginatedArrayList<Map<String, String>>();
		l.setPageSize(1);
		l.setIndex(page);
		l.setTotalItem(6);
		l.getStartRow();
		l.add(m);
		l.add(m1);
		l.add(m2);
		Result r = new Result("/nav/navList");
		r.addObject("list", l);
		return r;
	}
}
