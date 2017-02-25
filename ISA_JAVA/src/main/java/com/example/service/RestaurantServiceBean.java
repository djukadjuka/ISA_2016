package com.example.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.RestaurantBean;
import com.example.repository.RestaurantRepository;

@Service
public class RestaurantServiceBean implements RestaurantService{

	@Autowired
	private RestaurantRepository repository;
	
	@Override
	public Collection<RestaurantBean> findAll() {
		
		return repository.findAll();
	}

	@Override
	public RestaurantBean findOne(Long id) {
		RestaurantBean find = repository.findOne(id);
		return find;
	}

	@Override
	public RestaurantBean create(RestaurantBean restaurant) {
		
		RestaurantBean r = repository.save(restaurant);
		return r;
	}

	@Override
	public RestaurantBean update(RestaurantBean restaurant) {
		RestaurantBean existing = repository.getOne((long) restaurant.getId());
		if(existing == null){
			//ne postoji taj koji zelis da updateujes
			return null;
		}
		RestaurantBean updated = repository.save(restaurant);
		return updated;
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public Collection<RestaurantBean> filterRestaurantsByName(String name) {
		// TODO Auto-generated method stub
		return repository.filterRestaurantsByName(name);
	}

	@Override
	public Collection<RestaurantBean> filterRestaurantsByType(String type) {
		// TODO Auto-generated method stub
		return repository.filterRestaurantsByType(type);
	}

	@Override
	public Collection<RestaurantBean> filterRestaurantsByNameAndType(String name, String type) {
		// TODO Auto-generated method stub
		return repository.filterRestaurantsByNameAndType(name, type);
	}

	@Override
	public void createManagerRelation(Long man_id, Long rest_id) {
		this.repository.createManagerRelation(man_id, rest_id);
	}

	@Override
	public void updateRestaurantName_FIX(String name, Long id) {
		this.repository.updateRestaurantName_FIX(name, id);
	}

	@Override
	public void updateRestaurantType_FIX(String type, Long id) {
		this.repository.updateRestaurantType_FIX(type, id);
	}

	@Override
	public RestaurantBean getExistingFoodType(Long rest_id, Long food_id) {
		return this.repository.getExistingFoodType(rest_id, food_id);
	}

	@Override
	public void insert_new_food_type(Long rest_id, Long type_id) {
		this.repository.insert_new_food_type(rest_id, type_id);
	}

	@Override
	public void delete_food_type(Long rest_id, Long type_id) {
		this.repository.delete_food_type(rest_id, type_id);
	}

	@Override
	public void inset_drink_item(Long rest_id, Long drink_id) {
		this.repository.inset_drink_item(rest_id, drink_id);
	}

	@Override
	public void inset_food_item(Long rest_id, Long food_id) {
		this.repository.inset_food_item(rest_id, food_id);
	}

	@Override
	public void delete_drink_item(Long rest_id, Long drink_id) {
		this.repository.delete_drink_item(rest_id, drink_id);
	}

	@Override
	public void delete_food_item(Long rest_id, Long food_id) {
		this.repository.delete_food_item(rest_id, food_id);
	}

	@Override
	public void delete_zone_from_restaurant(Long rest_id, Long zone_id) {
		this.repository.delete_zone_from_restaurant(rest_id, zone_id);
	}

	@Override
	public void delete_tables_from_zone(Long zone_id) {
		this.repository.delete_tables_from_zone(zone_id);
	}

	@Override
	public void update_restaurant_coords(Double lat, Double lng, Long id) {
		this.repository.update_restaurant_coords(lat, lng, id);
	}

}
