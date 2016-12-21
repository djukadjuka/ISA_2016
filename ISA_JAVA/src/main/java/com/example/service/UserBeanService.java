package com.example.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.domain.UserBean;

public interface UserBeanService {

	Page<UserBean> findUserBeans(Pageable pageable);
	
	UserBean getUserBean(long id);
	
	Page<UserBean> getUserBeanByUsername(String username, Pageable pageable);
	
	UserBean getUserBeanByUsername(String username);

}
