package com.example.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.example.domain.UserBean;

public interface UserBeanRepository extends Repository<UserBean, Long>{

	public Page<UserBean> findAll(Pageable pageable);
	
	public UserBean findById(long id);
	
	public Page<UserBean> findByUsername(String username, Pageable pageable);
	
	public UserBean findByUsername(String username);
}
