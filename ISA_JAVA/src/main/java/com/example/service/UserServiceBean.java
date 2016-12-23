package com.example.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}