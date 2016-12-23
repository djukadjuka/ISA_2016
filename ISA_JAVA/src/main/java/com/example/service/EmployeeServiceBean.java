package com.example.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.domain.EmployeeBean;
import com.example.domain.UserBean;
import com.example.repository.EmployeeRepository;

public class EmployeeServiceBean  implements EmployeeService{
	
	@Autowired
	EmployeeRepository empRepo;

	@Override
	public EmployeeBean findOne(Long id) {
		// TODO Auto-generated method stub
		return empRepo.findOne(id);
	}

	@Override
	public Collection<EmployeeBean> findAll() {
		// TODO Auto-generated method stub
		return empRepo.findAll();
	}

	@Override
	public UserBean update(EmployeeBean employee) {
		// TODO Auto-generated method stub
	/*	UserBean u = empRepo.findOne(employee.getId());
		if(u == null)
			return null;
		return  empRepo.save(employee);*/
		return null;
	}

	@Override
	public UserBean create(EmployeeBean emloyee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

}
