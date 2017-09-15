package com.cr.pmp.service.permissions;

import java.util.Map;

import com.cr.pmp.common.result.Result;
import com.cr.pmp.model.permissions.Permissions;

public interface PermissionsService {

	public Result addPermissions(Permissions permissions);

	public Result queryPermissionsPageList(Map<String, Object> params);

	public Result delPermissions(Integer id);

	public Result updPermissions(Permissions permissions);

	public Result queryById(Integer id);

	public Result checkExistByCode(String code);

}
