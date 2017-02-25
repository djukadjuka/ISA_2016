package com.example.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.EmployeeEnum;
import com.example.domain.UserBean;
import com.example.repository.UserRepository;

@Service
public class UserServiceBean implements UserService{

	@Autowired
	UserRepository userRepo;
	
	@Override
	public UserBean findOne(Long id) {
		return userRepo.findOne(id);
	}

	@Override
	public Collection<UserBean> findAll() {
		return userRepo.findAll();
	}

	@Override
	public UserBean update(UserBean user) {
		UserBean u = userRepo.findOne(user.getId());
		if(u == null)
			return null;
		return userRepo.save(user);
	}

	@Override
	public UserBean create(UserBean user) {
		if(user.getId() != null){
			return null;
		}
		return userRepo.save(user);
	}

	@Override
	public void delete(Long id) {
		userRepo.delete(id);
	}
	
	public Collection<UserBean> getUsersNotManagingOrNotManagersForRestaurant(Long rest_id){
		return userRepo.getUsersNotManagingOrNotManagersForRestaurant(rest_id);
	}
	
	public Collection<UserBean> getManagersForRestaurantNoCurrentManager(Long rest_id, Long mgr_id){
		return userRepo.getManagersForRestaurantNoCurrentManager(rest_id, mgr_id);
	}

	@Override
	public Collection<UserBean> getUsersThatWorkForARestaurant(Long rest_id) {
		return userRepo.getUsersThatWorkForARestaurant(rest_id);
	}

	@Override
	public Collection<UserBean> getusersThatDoNotWorkForARestaurant() {
		return userRepo.getusersThatDoNotWorkForARestaurant();
	}

	@Override
	public Collection<UserBean> getPossibleDeliverers() {
		return userRepo.getPossibleDeliverers();
	}

	@Override
	public Collection<UserBean> getTiesToRestaurantByThisManager(Long manager_id) {
		return this.userRepo.getTiesToRestaurantByThisManager(manager_id);
	}

	@Override
	public void destroyManagerRestaurantTies(Long manager_id, Long rest_id) {
		this.userRepo.destroyManagerRestaurantTies(manager_id, rest_id);
	}

	@Override
	public void fireManagerAllTogether(Long user_id) {
		this.userRepo.fireManagerAllTogether(user_id);
	}

}
