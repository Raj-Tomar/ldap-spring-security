package com.raj.user.dao;

import java.util.List;

import com.raj.beans.UserBean;

public interface UserDao {

	public String saveOrUpdateUser(UserBean user);
	public List<UserBean> getAllUser();
	public UserBean getUserById(Integer id);
	public String deleteUser(Integer id);
}
