package com.example.service;

import java.util.Collection;

import com.example.domain.RestaurantBean;

public interface RestaurantService {

	Collection<RestaurantBean> findAll();
	
	Collection<RestaurantBean> filterRestaurantsByName(String name);
	
	Collection<RestaurantBean> filterRestaurantsByType(String type);
		
	Collection<RestaurantBean> filterRestaurantsByNameAndType(String name, String type);
	
	RestaurantBean findOne(Long id);
	
	RestaurantBean create(RestaurantBean restaurant);
	
	RestaurantBean update(RestaurantBean restaurant);
	
	void delete(Long id);
}
