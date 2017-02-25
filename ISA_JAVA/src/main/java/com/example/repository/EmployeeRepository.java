package com.example.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.EmployeeBean;
import com.example.domain.EmployeeEnum;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeBean, Long> {

	/**
	 * Menadzer koji je pokusao da registruje restoran
	 * GET -	vraca samo id tog menadzera - koristi se da se nakon prihvacene registracije
	 * 			restorana doda menadzer tog restorana
	 * */
	@Query(value=""
			+ "SELECT e.user_id, e.suit_size, e.shoe_size, e.role, e.date_of_birth"
			+ " from employee e, registering_restaurants r"
			+ " where r.rest_id = :registry_id"
			+ " and e.user_id = r.manager_id",nativeQuery = true)
	public EmployeeBean getMGR_fromRestaurantREGISTRY(@Param("registry_id") Long registry_id);
	
	/**
	 * SELECT EMPLOYEES that work for a restaurant and are not managers
	 * */
	@Query(value ="SELECT * FROM employee e WHERE e.works_in_restaurant = :rest_id",nativeQuery=true)
	public Collection<EmployeeBean> getWorkersThatWorkForARestaurant(@Param("rest_id") Long rest_id);
	
	/**
	 * SELECT ALL WAITERS for a restaurant
	 * */
	@Query(value = "SELECT * from employee e where e.works_in_restaurant = :rest_id and e.role = 'WAITER'",nativeQuery=true)
	public Collection<EmployeeBean> getWaitersForARestaurant(@Param("rest_id") Long rest_id);
	
	/**
	 * SELECT EMPLOYEES that do not work for a restaurant
	 * */
	@Query(value ="SELECT * FROM employee e WHERE"
				+ " e.role != 'MANAGER' AND e.works_in_restaurant IS NULL",nativeQuery=true)
	public Collection<EmployeeBean> getWorkersThatDoNotWorkForARestaurant();
	
	/**delete deliverer that is now manager*/
	@Transactional
	@Modifying
	@Query(value = "delete from deliverer where user_id = :user_id",nativeQuery = true)
	public void delete_deliverer_that_became_manager(@Param("user_id") Long user_id);
	
	/**check if employee is already manager*/
	@Query(value = "select * from employee where user_id = :user_id",nativeQuery = true)
	public EmployeeBean check_if_user_already_manager(@Param("user_id") Long user_id);
	
	/**add a new manager*/
	@Transactional
	@Modifying
	@Query(value="insert into employee (date_of_birth,role,shoe_size,suit_size,user_id,works_in_restaurant)"
			+ " values(null,'MANAGER',null,null,:user_id,null)",nativeQuery=true)
	public void insert_new_manager(@Param("user_id") Long user_id);
	
	/**create new manager relation*/
	@Transactional
	@Modifying
	@Query(value = "insert into manages_restaurants(manager_id,rest_id) values(:manager_id,:rest_id)",nativeQuery=true)
	public void create_new_manager_relation(@Param("manager_id") Long manager_id, @Param("rest_id") Long rest_id);
	
	/**create new employee*/
	@Transactional
	@Modifying
	@Query(value="insert into employee(date_of_birth,role,shoe_size,suit_size,user_id,works_in_restaurant)"
			+ " values(:date_of_birth,:role,:shoe_size,:suit_size,:user_id,:works_in_restaurant)",nativeQuery=true)
	public void create_new_worker_for_restaurant(	@Param("date_of_birth") Long date_of_birth,
													@Param("role") String role,
													@Param("shoe_size") Float shoe_size,
													@Param("suit_size") Float suit_size,
													@Param("user_id") Long user_id,
													@Param("works_in_restaurant") Long works_in_restaurant);
	
	/**Discontinue an employees fate ...*/
	@Transactional
	@Modifying
	@Query(value="delete from employee where user_id = :user_id",nativeQuery=true)
	public void fire_an_employee(@Param("user_id") Long user_id);
	
	@Transactional
	@Modifying
	@Query(value="delete from review where for_employee = :user_id",nativeQuery=true)
	public void delete_his_reviews(@Param("user_id") Long user_id);
	
	@Transactional
	@Modifying
	@Query(value="delete from restaurant_table where served_by = :user_id",nativeQuery=true)
	public void delete_his_tables(@Param("user_id") Long user_id);
	
	@Transactional
	@Modifying
	@Query(value="delete from employee_schedule where for_employee = :user_id",nativeQuery=true)
	public void delete_his_schedules(@Param("user_id") Long user_id);
	
	
}
