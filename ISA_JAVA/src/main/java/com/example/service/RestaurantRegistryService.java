package com.example.service;

import java.util.Collection;

import com.example.domain.RestaurantRegistry;

public interface RestaurantRegistryService {

	Collection<RestaurantRegistry> findAll();
	
	RestaurantRegistry findOne(Long id);
	
	RestaurantRegistry create(RestaurantRegistry restaurant);
	
	RestaurantRegistry update(RestaurantRegistry restaurant);
	
	void delete(Long id);
	
	//CUSTOM QUERIES
	Collection<RestaurantRegistry> getRegisteredRestaurantsByManagerId(Long id);
	public Collection<RestaurantRegistry> getNotDecidedRestaurants();
	public int updateRestaurantSeen(Long rest_id);
	public int updateRestaurantStatus_ACCEPTED(Long rest_id);
	public int updateRestaurantStatus_DECLINED(Long rest_id);
	//public EmployeeBean getMANAGER_byIDOFHISREG(Long registry_id);
	
}
