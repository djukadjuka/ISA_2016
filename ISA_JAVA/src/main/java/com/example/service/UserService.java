package com.example.service;

import java.util.Collection;

import com.example.domain.UserBean;

public interface UserService {

	UserBean findOne(Long id);
	
	Collection<UserBean> findAll();
	
	UserBean update(UserBean user);
	
	UserBean create(UserBean user);
	
	void delete(Long id);
	
}
