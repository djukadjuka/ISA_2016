package com.example.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.domain.EmployeeBean;

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
}
