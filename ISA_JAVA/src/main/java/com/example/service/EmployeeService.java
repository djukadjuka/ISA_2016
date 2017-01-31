package com.example.service;

import java.util.Collection;

import com.example.domain.EmployeeBean;
import com.example.domain.UserBean;

public interface EmployeeService {


	EmployeeBean findOne(Long id);
	
	Collection<EmployeeBean> findAll();
	
	UserBean update(EmployeeBean employee);
	
	UserBean create(EmployeeBean emloyee);
	
	void delete(Long id);
}
