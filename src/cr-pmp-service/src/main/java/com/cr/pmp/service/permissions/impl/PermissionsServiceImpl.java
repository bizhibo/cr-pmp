package com.cr.pmp.service.permissions.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cr.pmp.common.result.Result;
import com.cr.pmp.common.utils.LogUtils;
import com.cr.pmp.common.utils.PaginatedArrayList;
import com.cr.pmp.common.utils.PaginatedList;
import com.cr.pmp.dao.permissions.PermissionsDao;
import com.cr.pmp.model.permissions.Permissions;
import com.cr.pmp.service.permissions.PermissionsService;

@Service("permissionsService")
public class PermissionsServiceImpl implements PermissionsService {

	@Autowired
	private PermissionsDao permissionsDao;

	@Override
	public Result addPermissions(Permissions permissions) {
		Result result = new Result();
		try {
			Integer flag = permissionsDao.addPermissions(permissions);
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

	@Transactional(rollbackFor = Exception.class)
	@Override
	public Result delPermissions(Integer id) {
		Result result = new Result();
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("jid", id);
			Integer flag = permissionsDao.delPermissions(id);
			permissionsDao.delUserPermissions(params);
			if (flag > 0) {
				result.setResultCode(true);
			} else {
				result.setResultCode(false);
				throw new RuntimeException("----- delete Permissions Transactional rollback -----");
			}
		} catch (Exception e) {
			result.setResultCode(false);
			LogUtils.error(e.getMessage(), e);
			throw e;
		}
		return result;
	}

	@Override
	public Result queryById(Integer id) {
		Result result = new Result();
		try {
			Permissions permissions = permissionsDao.queryById(id);
			result.addObject("permissions", permissions);
		} catch (Exception e) {
			LogUtils.error(e.getMessage(), e);
		}
		return result;
	}

	@Override
	public Result queryPermissionsPageList(Map<String, Object> params) {
		Result result = new Result();
		try {
			int count = permissionsDao.queryCount(params);
			if (params.get("page") == null || params.get("page").equals("")
					|| params.get("page").equals("0")) {
				params.put("page", PaginatedArrayList.PAGEINDEX_DEFAULT);
			}
			if (params.get("pageSize") == null
					|| params.get("pageSize").equals("")
					|| params.get("pageSize").equals("0")) {
				params.put("pageSize", PaginatedArrayList.PAGESIZE_DEFAULT);
			}
			PaginatedList<Permissions> pageList = new PaginatedArrayList<Permissions>(
					Integer.valueOf(params.get("page").toString()),
					Integer.valueOf(params.get("pageSize").toString()), count);
			params.put("startRow",
					pageList.getStartRow() > 0 ? pageList.getStartRow() - 1
							: pageList.getStartRow());
			List<Permissions> permissionsList = permissionsDao
					.queryPermissionsPageList(params);
			pageList.addAll(permissionsList);
			result.addObject("pageList", pageList);
			result.addObject("params", params);
		} catch (Exception e) {
			LogUtils.error(e.getMessage(), e);
		}
		return result;
	}

	@Override
	public Result updPermissions(Permissions permissions) {
		Result result = new Result();
		try {
			Integer flag = permissionsDao.updPermissions(permissions);
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
	public Result checkExistByCode(String code) {
		Result result = new Result();
		try {
			Integer flag = permissionsDao.queryExistByCode(code);
			if (flag > 0) {
				result.setResultCode("exist");
			}
		} catch (Exception e) {
			LogUtils.error(e.getMessage(), e);
		}
		return result;
	}

}
