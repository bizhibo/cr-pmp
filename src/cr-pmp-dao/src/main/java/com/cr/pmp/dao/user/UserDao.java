package com.cr.pmp.dao.user;

import java.util.List;
import java.util.Map;

import com.cr.pmp.model.user.User;

public interface UserDao {

	public User login(Map<String, Object> params);

	public List<User> queryAllUser();

	public User queryUserInfo(String userName);

	public List<User> queryUserPageList(Map<String, Object> params);

	public Integer queryCount(Map<String, Object> params);

	public Integer addUser(User user);

	public Integer delUser(String userName);
}
