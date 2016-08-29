package com.cr.pmp.model.user;

import java.util.Date;

import com.cr.pmp.common.base.BaseModel;

/**
 * @描述 : 用户实体
 * @创建者：cr-pmp
 * @创建时间： 2016年8月21日下午9:49:42
 *
 */
public class User extends BaseModel {

	/** 用户名 **/
	private String userName;
	/** 密码 **/
	private String password;
	/** 姓名 **/
	private String name;
	/** 生日 **/
	private Date birthday;
	/** 性别 **/
	private String sex;
	/** 所属部门id **/
	private int deptId;
	/** 邮箱 **/
	private String email;
	/** 手机号码 **/
	private String phone;
	/** 职位 **/
	private String position;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

}
