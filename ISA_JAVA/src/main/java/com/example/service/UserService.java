package com.example.service;

import java.util.Collection;

import org.springframework.data.repository.query.Param;

import com.example.domain.UserBean;

public interface UserService {

	UserBean findOne(Long id);
	
	Collection<UserBean> findAll();
	
	UserBean update(UserBean user);
	
	UserBean create(UserBean user);
	
	void delete(Long id);
	
	//CUSTOM
	public Collection<UserBean> getUsersNotManagingOrNotManagersForRestaurant(Long rest_id);
	public Collection<UserBean> getManagersForRestaurantNoCurrentManager(Long rest_id, Long mgr_id);
	
	public Collection<UserBean> getUsersThatWorkForARestaurant(Long rest_id);
	public Collection<UserBean> getusersThatDoNotWorkForARestaurant();
	
}
