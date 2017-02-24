package com.example.service;

import java.util.Collection;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.EmployeeScheduleBean;
import com.example.repository.EmployeeScheduleRepository;

@Service
public class EmployeeScheduleServiceBean implements EmployeeScheduleService{

	@Autowired
	private EmployeeScheduleRepository repository;
	
	@Override
	public EmployeeScheduleBean findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public Collection<EmployeeScheduleBean> findAll() {
		return repository.findAll();
	}

	@Override
	public EmployeeScheduleBean update(EmployeeScheduleBean schedz) {
		return repository.save(schedz);
	}

	@Override
	public EmployeeScheduleBean create(EmployeeScheduleBean shedz) {
		return repository.save(shedz);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public Collection<EmployeeScheduleBean> getScheddzzzForEmployee(Long employee_id) {
		return repository.getScheddzzzForEmployee(employee_id);
	}
	@Override
	public  Collection<EmployeeScheduleBean> getSchedduleForEmployee(Long date){
		return repository.getSchedduleForEmployee( date);
	}
	
	@Override
	public  Collection<EmployeeScheduleBean> getSchedduleForCookEmployee(Long date){
		return repository.getSchedduleForEmployee( date);
	}
	
	@Override
	public  Collection<EmployeeScheduleBean> getSchedduleForBarmanEmployee(Long date){
		return repository.getSchedduleForEmployee( date);
	}



	

}
