package com.cr.pmp.service.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cr.pmp.common.dict.SystemDict;
import com.cr.pmp.common.result.Result;
import com.cr.pmp.common.utils.LogUtils;
import com.cr.pmp.common.utils.PaginatedArrayList;
import com.cr.pmp.common.utils.PaginatedList;
import com.cr.pmp.common.utils.SecurityUtils;
import com.cr.pmp.dao.permissions.PermissionsDao;
import com.cr.pmp.dao.user.UserDao;
import com.cr.pmp.model.permissions.Permissions;
import com.cr.pmp.model.permissions.UserPermissions;
import com.cr.pmp.model.user.User;
import com.cr.pmp.service.user.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private PermissionsDao permissionsDao;

	@Override
	public Result login(Map<String, Object> params, HttpSession session) {
		Result result = new Result();
		try {
			String md5PW = SecurityUtils.md5(params.get("password").toString());
			params.put("password", md5PW);
			User user = userDao.login(params);
			if (user != null) {
				Map<String, String> ujMap = new HashMap<String, String>();
				List<UserPermissions> userPermissionss = permissionsDao
						.queryPermissionsByUserName(user.getUserName());
				for (UserPermissions userPermissions : userPermissionss) {
					ujMap.put(userPermissions.getCode(),
							userPermissions.getUserName());
				}
				session.setAttribute(SystemDict.USERSESSIONKEY, user);
				session.setAttribute(SystemDict.USERPERMISSIONSKEY, ujMap);
				session.setMaxInactiveInterval(60 * 120);
				result.setResultCode(true);
				result.setMessage("登陆成功!");
			} else {
				result.setResultCode(false);
				result.setMessage("用户名或密码错误!");
			}
		} catch (Exception e) {
			LogUtils.error(e.getMessage(), e);
		}
		return result;
	}

	@Override
	public Result queryAllUser() {
		Result result = new Result();
		try {
			List<User> users = userDao.queryAllUser();
			result.addObject("users", users);
		} catch (Exception e) {
			LogUtils.error(e.getMessage(), e);
		}
		return result;
	}

	@Override
	public Result queryUserPageList(Map<String, Object> params) {
		Result result = new Result();
		try {
			int count = userDao.queryCount(params);
			if (params.get("page") == null || params.get("page").equals("")
					|| params.get("page").equals("0")) {
				params.put("page", PaginatedArrayList.PAGEINDEX_DEFAULT);
			}
			if (params.get("pageSize") == null
					|| params.get("pageSize").equals("")
					|| params.get("pageSize").equals("0")) {
				params.put("pageSize", PaginatedArrayList.PAGESIZE_DEFAULT);
			}
			PaginatedList<User> pageList = new PaginatedArrayList<User>(
					Integer.valueOf(params.get("page").toString()),
					Integer.valueOf(params.get("pageSize").toString()), count);
			params.put("startRow",
					pageList.getStartRow() > 0 ? pageList.getStartRow() - 1
							: pageList.getStartRow());
			List<User> userList = userDao.queryUserPageList(params);
			pageList.addAll(userList);
			result.addObject("pageList", pageList);
			result.addObject("params", params);
		} catch (Exception e) {
			LogUtils.error(e.getMessage(), e);
		}
		return result;
	}

	@Override
	public Result addUser(User user) {
		Result result = new Result();
		try {
			int flag = userDao.addUser(user);
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
	public Result queryUserInfo(String userName) {
		Result result = new Result();
		try {
			User user = userDao.queryUserInfo(userName);
			result.addObject("user", user);
		} catch (Exception e) {
			LogUtils.error(e.getMessage(), e);
		}
		return result;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public Result delUser(String userName) {
		Result result = new Result();
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userName", userName);
			Integer flag = userDao.delUser(userName);
			permissionsDao.delUserPermissions(params);
			if (flag > 0) {
				result.setResultCode(true);
			} else {
				result.setResultCode(false);
				throw new RuntimeException(
						"----- delete Permissions Transactional rollback -----");
			}
		} catch (Exception e) {
			result.setResultCode(false);
			LogUtils.error(e.getMessage(), e);
			throw e;
		}
		return result;
	}

	@Override
	public Result checkUserExist(Map<String, Object> params) {
		Result result = new Result();
		try {
			Integer flag = userDao.queryCount(params);
			if (flag > 0) {
				result.setResultCode("exist");
			}
		} catch (Exception e) {
			LogUtils.error(e.getMessage(), e);
		}
		return result;
	}

	@Override
	public Result checkPassword(Map<String, Object> params) {
		Result result = new Result();
		try {
			String md5PW = SecurityUtils.md5(params.get("password").toString());
			params.put("password", md5PW);
			User user = userDao.login(params);
			if (user != null) {
				result.setResultCode(true);
			} else {
				result.setResultCode(false);
			}
		} catch (Exception e) {
			LogUtils.error(e.getMessage(), e);
		}
		return result;
	}

	@Override
	public Result updUser(User user) {
		Result result = new Result();
		try {
			String md5PW = SecurityUtils.md5(user.getPassword());
			user.setPassword(md5PW);
			Integer flag = userDao.updUser(user);
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
	public Result queryUserPermissions(String userName) {
		Result result = new Result();
		try {
			List<Permissions> juList = permissionsDao.queryPermissionsList();
			List<UserPermissions> ujList = permissionsDao
					.queryPermissionsByUserName(userName);
			result.addObject("juList", juList);
			result.addObject("ujList", ujList);
		} catch (Exception e) {
			LogUtils.error(e.getMessage(), e);
		}
		return result;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public Result addUserPermissions(List<UserPermissions> userPermissionss,
			String userName) {
		Result result = new Result();
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userName", userName);
			permissionsDao.delUserPermissions(params);
			permissionsDao.addUserPermissions(userPermissionss);
		} catch (Exception e) {
			result.setResultCode(false);
			LogUtils.error(e.getMessage(), e);
			throw e;
		}
		return result;
	}
}
