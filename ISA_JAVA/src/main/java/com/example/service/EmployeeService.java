package com.example.service;

import java.util.Collection;

import org.springframework.data.repository.query.Param;

import com.example.domain.EmployeeBean;
import com.example.domain.EmployeeEnum;
import com.example.domain.UserBean;

public interface EmployeeService {


	EmployeeBean findOne(Long id);
	
	Collection<EmployeeBean> findAll();
	
	EmployeeBean update(EmployeeBean employee);
	
	EmployeeBean create(EmployeeBean emloyee);
	
	void delete(Long id);
	
	public EmployeeBean getMGR_fromRestaurantREGISTRY(Long registry_id);
	
	//CUSTOM
	public Collection<EmployeeBean> getWorkersThatWorkForARestaurant(Long rest_id);
	public Collection<EmployeeBean> getEmployeeById(Long worker_id);
	public Collection<EmployeeBean> getWorkersThatDoNotWorkForARestaurant();
	public Collection<EmployeeBean> getWaitersForARestaurant(Long rest_id);
	public void delete_deliverer_that_became_manager(Long user_id);
	public EmployeeBean check_if_user_already_manager(Long user_id);
	public void insert_new_manager(Long user_id);
	public void create_new_manager_relation(Long manager_id, Long rest_id);
	public void create_new_worker_for_restaurant(	Long date_of_birth,
			String str,
			Float shoe_size,
			Float suit_size,
			Long user_id,
			Long works_in_restaurant);
	public void fire_an_employee(Long user_id);
	public void delete_his_reviews(Long user_id);
	public void delete_his_tables(Long user_id);
	public void delete_his_schedules(Long user_id);
}
