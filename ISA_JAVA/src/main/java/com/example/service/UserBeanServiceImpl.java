package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.example.domain.UserBean;
import com.example.repository.UserBeanRepository;

@Service
@Transactional
public class UserBeanServiceImpl implements UserBeanService {

	@Autowired
	private UserBeanRepository userRepository;
	
	@Override
	public Page<UserBean> findUserBeans(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	@Override
	public UserBean getUserBean(long id) {
		Assert.notNull(id,"ID CANNOT BE NULL");
		return userRepository.findById(id);
	}

	@Override
	public Page<UserBean> getUserBeanByUsername(String username, Pageable pageable) {
		Assert.notNull(username,"USERNAME CANNOT BE NULL");
		return userRepository.findByUsername(username, pageable);
	}

	@Override
	public UserBean getUserBeanByUsername(String username) {
		Assert.notNull(username,"USERNAME CANNOT BE NULL");
		return userRepository.findByUsername(username);
	}

}
