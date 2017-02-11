package com.example.service;

import java.util.Collection;

import com.example.domain.RestaurantZonesRelation;

public interface ZoneService {
	Collection<RestaurantZonesRelation> findAll();
	
	RestaurantZonesRelation findOne(Long id);
	
	RestaurantZonesRelation create(RestaurantZonesRelation zoneRelation);
	
	RestaurantZonesRelation update(RestaurantZonesRelation zoneRelation);
	
	void delete(Long id);
}
