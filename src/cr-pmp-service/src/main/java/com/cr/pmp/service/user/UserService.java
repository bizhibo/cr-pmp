package com.cr.pmp.service.user;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.cr.pmp.common.result.Result;
import com.cr.pmp.model.permissions.UserPermissions;
import com.cr.pmp.model.user.User;

/**
 * @描述 : 用户相关业务service
 * @创建者：cr-pmp
 * @创建时间： 2016年8月23日下午1:52:25
 *
 */
public interface UserService {

	/**
	 * @描述 : 登陆
	 * @创建者：cr-pmp
	 * @创建时间： 2016年8月23日下午1:54:01
	 *
	 * @param param
	 * @return
	 */
	public Result login(Map<String, Object> params, HttpSession session);

	public Result queryAllUser();

	public Result queryUserPageList(Map<String, Object> params);

	public Result addUser(User user);

	public Result queryUserInfo(String userName);

	public Result delUser(String userName);

	public Result checkUserExist(Map<String, Object> params);

	public Result checkPassword(Map<String, Object> params);
	
	public Result updUser(User user);
	
	public Result queryUserPermissions(String userName);
	
	public Result addUserPermissions(List<UserPermissions> userPermissionss,
			String userName);
}
