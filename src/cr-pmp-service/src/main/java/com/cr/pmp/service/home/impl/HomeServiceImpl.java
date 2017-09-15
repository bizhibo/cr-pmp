package com.cr.pmp.service.home.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cr.pmp.common.dict.FilePathDict;
import com.cr.pmp.common.result.Result;
import com.cr.pmp.dao.navigation.NavigationDao;
import com.cr.pmp.model.soft.SoftWare;
import com.cr.pmp.service.home.HomeService;

@Service("homeService")
public class HomeServiceImpl implements HomeService {

	@Autowired
	private NavigationDao navigationDao;

	@Override
	public Result index() {
		Result result = new Result();
		return result;
	}

	@Override
	public Result getSoftWare(HttpServletRequest request) {
		Result result = new Result();
		String softwarePath = request.getSession().getServletContext()
				.getRealPath(FilePathDict.SOFTWAREFILEPATH);
		File file = new File(softwarePath);
		File[] tempList = file.listFiles();
		List<SoftWare> softWares = new ArrayList<SoftWare>();
		for (File softFile : tempList) {
			SoftWare softWare = new SoftWare();
			softWare.setCreateTime(new Date(softFile.lastModified()));
			softWare.setName(softFile.getName());
			softWare.setSoftWareUrl(FilePathDict.SOFTWAREFILEPATH
					+ softFile.getName());
			softWares.add(softWare);
		}
		result.addObject("softWares", softWares);
		return result;
	}
}
