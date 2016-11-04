package com.raj.user.controller;

import java.util.List;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.raj.beans.UserBean;
import com.raj.user.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userSerivce;
	
	private static Logger logger = Logger.getLogger(UserController.class);
	
	@GetMapping(value="/user")
	public Callable<String> home(Model model){
		logger.info("home controller");
		try {
			model.addAttribute("user", new UserBean());
			model.addAttribute("userList", userSerivce.getAllUser());
		} catch(Exception e) {
			logger.error("Exception: "+e.getMessage());
		}
		return () -> {
			return "user";
		};
	}
	
	@GetMapping(value="/userlist")
	@ResponseBody
	public List<UserBean> getAllUser(){
		logger.info("getAllUser controller");
		List<UserBean> userList = null;
		try {
			userList = userSerivce.getAllUser();
		} catch(Exception e) {
			logger.error("Exception: "+e.getMessage());
		}
		return userList;
	}
	
	@PostMapping(value="/saveOrUpdateUser")
	public Callable<String> saveOrUpdateUser(@ModelAttribute("user") UserBean user){
		logger.info("saveOrUpdateUser controller");
		try {
			String status = userSerivce.saveOrUpdateUser(user);
			logger.info("Status of saveOrUpdate: "+status);
		} catch(Exception e) {
			logger.error("Exception: "+e.getMessage());
		}
		return () -> {
			return "redirect:/user";
		};
	}
	
	@GetMapping(value="/updateUser/{id}")
	public String getUserById(@PathVariable("id")Integer id, Model model){
		logger.info("getUserById in controller");
		String status = null;
		UserBean bean = null;
		try {
			bean = userSerivce.getUserById(id);
			if(null == bean){
				status = "redirect:/user";
			} else{
				model.addAttribute("user", bean);
				status = "user";
			}
		} catch (Exception e) {
			logger.error("Exception: "+e.getMessage());
		}
		return status;
	}
	
	@GetMapping(value="/deleteUser/{id}")
	public Callable<String> deleteUser(@PathVariable("id")Integer id){
		logger.info("deleteUser in controller");
		try {
			String status = userSerivce.deleteUser(id);
			logger.info("deleteUser Status: "+status);
		} catch (Exception e) {
			logger.error("Exception : "+e.getMessage());
		}
		return () -> {
			return "redirect:/user";
		};
	}
}
