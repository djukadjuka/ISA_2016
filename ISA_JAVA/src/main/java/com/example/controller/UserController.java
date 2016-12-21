package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.service.UserBeanService;

@Controller
public class UserController {
	
	@Autowired
	UserBeanService userService;
	
	@GetMapping("/getUsers")
	@ResponseBody
	@Transactional(readOnly = true)
	public String getUser(){
		
		return this.userService.getUserBeanByUsername("UserName1").getEmail();
	}
}
