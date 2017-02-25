package com.example.service;

import java.util.Collection;

import org.springframework.data.repository.query.Param;

import com.example.domain.EmployeeEnum;
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
	public Collection<UserBean> getPossibleDeliverers();
	public Collection<UserBean> getTiesToRestaurantByThisManager(Long manager_id);
	public void destroyManagerRestaurantTies(Long manager_id,Long rest_id);
	public void fireManagerAllTogether(Long user_id);
	
}
