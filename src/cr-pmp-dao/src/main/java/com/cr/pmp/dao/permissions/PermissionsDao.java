package com.cr.pmp.dao.permissions;

import java.util.List;
import java.util.Map;

import com.cr.pmp.model.permissions.Permissions;
import com.cr.pmp.model.permissions.UserPermissions;

public interface PermissionsDao {

	public Integer addPermissions(Permissions permissions);

	public Integer updPermissions(Permissions permissions);

	public Integer delPermissions(Integer id);

	public Integer queryCount(Map<String, Object> params);

	public List<Permissions> queryPermissionsPageList(
			Map<String, Object> params);

	public List<Permissions> queryPermissionsList();

	public Permissions queryById(Integer id);

	public Integer queryExistByCode(String code);

	public Integer addUserPermissions(List<UserPermissions> userPermissionss);

	public Integer delUserPermissions(Map<String, Object> params);

	public List<UserPermissions> queryPermissionsByUserName(String userName);

}
