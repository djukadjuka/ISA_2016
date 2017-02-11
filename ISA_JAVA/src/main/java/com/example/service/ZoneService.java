package com.example.service;

import java.util.Collection;

import com.example.domain.RestaurantZoneBean;

public interface ZoneService {

	RestaurantZoneBean findOne(Long id);
	
	Collection<RestaurantZoneBean> findByRestaurant_id(Long restaurant_id);
	
	Collection<RestaurantZoneBean> findAll();
	
	RestaurantZoneBean update(RestaurantZoneBean zone);
	
	RestaurantZoneBean create(RestaurantZoneBean zone);
	
	void delete(Long id);
	
}
