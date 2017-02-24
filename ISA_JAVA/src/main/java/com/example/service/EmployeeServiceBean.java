package com.example.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.EmployeeBean;
import com.example.domain.UserBean;
import com.example.repository.EmployeeRepository;

@Service
public class EmployeeServiceBean  implements EmployeeService{
	
	@Autowired
	private EmployeeRepository empRepo;

	@Override
	public EmployeeBean findOne(Long id) {
		return empRepo.findOne(id);
	}

	@Override
	public Collection<EmployeeBean> findAll() {
		return empRepo.findAll();
	}

	@Override
	public EmployeeBean update(EmployeeBean employee) {
		return empRepo.save(employee);
	}

	@Override
	public EmployeeBean create(EmployeeBean emloyee) {
		return empRepo.save(emloyee);
	}

	@Override
	public void delete(Long id) {
		empRepo.delete(id);
	}
	
	public EmployeeBean getMGR_fromRestaurantREGISTRY(Long registry_id){
		return empRepo.getMGR_fromRestaurantREGISTRY(registry_id);
	}

	@Override
	public Collection<EmployeeBean> getWorkersThatWorkForARestaurant(Long rest_id) {
		return empRepo.getWorkersThatWorkForARestaurant(rest_id);
	}

	@Override
	public Collection<EmployeeBean> getWorkersThatDoNotWorkForARestaurant() {
		return empRepo.getWorkersThatDoNotWorkForARestaurant();
	}

	@Override
	public Collection<EmployeeBean> getWaitersForARestaurant(Long rest_id) {
		return empRepo.getWaitersForARestaurant(rest_id);
	}
	
	

}
