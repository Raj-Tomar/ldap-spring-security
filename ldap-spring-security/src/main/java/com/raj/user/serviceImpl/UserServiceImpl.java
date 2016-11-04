package com.raj.user.serviceImpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raj.beans.UserBean;
import com.raj.user.dao.UserDao;
import com.raj.user.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	private static Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Override
	public String saveOrUpdateUser(UserBean user) {
		logger.info("saveOrUpdateUser serviceImpl");
		String status = null;
		try{
			status = userDao.saveOrUpdateUser(user);
			logger.info("saveOrUpdateUser Status in serviceImpl"+status);
		} catch(Exception e){
			logger.error("Exception: "+e.getMessage());
		}
		return null;
	}
	
	@Override
	public List<UserBean> getAllUser() {
		logger.info("getAllUser serviceImpl");
		List<UserBean> userList = null;
		try{
			userList = userDao.getAllUser();
			logger.info("Total User: "+userList.size());
		} catch(Exception e){
			logger.error("Exception: "+e.getMessage());
		}
		return userList;
	}

	@Override
	public UserBean getUserById(Integer id) {
		logger.info("getAllUser serviceImpl");
		UserBean bean = null;
		try {
			bean = userDao.getUserById(id);
		} catch (Exception e) {
			logger.error("Exception: "+e.getMessage());
		}
		return bean;
	}

	@Override
	public String deleteUser(Integer id) {
		logger.info("getAllUser serviceImpl");
		String status = null;
		try {
			status = userDao.deleteUser(id);
		} catch (Exception e) {
			logger.error("Exception: "+e.getMessage());
		}
		return status;
	}
}
