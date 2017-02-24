package com.example.service;

import java.util.Collection;
import java.util.HashMap;

import com.example.domain.EmployeeScheduleBean;

public interface EmployeeScheduleService {

	EmployeeScheduleBean findOne(Long id);
	
	Collection<EmployeeScheduleBean> findAll();
	
	EmployeeScheduleBean update(EmployeeScheduleBean schedz);
	
	EmployeeScheduleBean create(EmployeeScheduleBean shedz);
	
	void delete(Long id);
	
	//CUSTOM
	public Collection<EmployeeScheduleBean> getScheddzzzForEmployee(Long employee_id);
	
	public Collection<EmployeeScheduleBean> getSchedduleForEmployee(Long date);
	
	public Collection<EmployeeScheduleBean> getSchedduleForCookEmployee(Long date);
	
	public Collection<EmployeeScheduleBean> getSchedduleForBarmanEmployee(Long date);
}
