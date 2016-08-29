package com.cr.pmp.dao.user;

import java.util.Map;

import com.cr.pmp.model.user.User;

public interface UserDao {

	public User login(Map<String, Object> params);
}
