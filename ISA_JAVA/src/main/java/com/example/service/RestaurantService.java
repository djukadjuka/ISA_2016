package com.example.service;

import java.util.Collection;

import com.example.domain.RestaurantBean;

public interface RestaurantService {

	Collection<RestaurantBean> findAll();
	
	Collection<RestaurantBean> filterRestaurants(String name);
	
	RestaurantBean findOne(Long id);
	
	RestaurantBean create(RestaurantBean restaurant);
	
	RestaurantBean update(RestaurantBean restaurant);
	
	void delete(Long id);
}
