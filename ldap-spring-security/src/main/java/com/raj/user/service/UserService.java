package com.raj.user.service;

import java.util.List;

import com.raj.beans.UserBean;

public interface UserService {

	public String saveOrUpdateUser(UserBean user);
	public List<UserBean> getAllUser();
	public UserBean getUserById(Integer id);
	public String deleteUser(Integer id);
}
