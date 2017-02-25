package com.example.service;

import java.util.Collection;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

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
	
	public void createManagerRelation(Long man_id,Long rest_id);
	
	//custom
	public void updateRestaurantName_FIX(String name, Long id);
	public void updateRestaurantType_FIX(String type, Long id);
	public RestaurantBean getExistingFoodType(Long rest_id,Long food_id);
	public void insert_new_food_type(Long rest_id, Long type_id);
	public void delete_food_type(Long rest_id, Long type_id);
	
	public void inset_drink_item(Long rest_id,  Long drink_id);
	public void inset_food_item(Long rest_id, Long food_id);
	public void delete_drink_item(Long rest_id, Long drink_id);
	public void delete_food_item(Long rest_id, Long food_id);
	
	public void delete_zone_from_restaurant(Long rest_id, Long zone_id);
	public void delete_tables_from_zone(Long zone_id);
}
