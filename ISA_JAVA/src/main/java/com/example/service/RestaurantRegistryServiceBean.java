package com.example.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.example.domain.EmployeeBean;
import com.example.domain.RestaurantRegistry;
import com.example.repository.RestaurantRegistryRepository;

@Service
public class RestaurantRegistryServiceBean implements RestaurantRegistryService{

	@Autowired
	private RestaurantRegistryRepository repository;
	
	@Override
	public Collection<RestaurantRegistry> findAll() {
		return repository.findAll();
	}

	@Override
	public RestaurantRegistry findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public RestaurantRegistry create(RestaurantRegistry restaurant) {
		return repository.save(restaurant);
	}

	@Override
	public RestaurantRegistry update(RestaurantRegistry restaurant) {
		return repository.save(restaurant);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public Collection<RestaurantRegistry> getRegisteredRestaurantsByManagerId(Long id) {
		return repository.getRegistersByManagerId(id);
	}

	@Override
	public Collection<RestaurantRegistry> getNotDecidedRestaurants() {
		return repository.getNotDecidedRestaurants();
	}

	@Override
	public int updateRestaurantSeen(Long rest_id) {
		return repository.updateRestaurantSeen(rest_id);
	}

	@Override
	public int updateRestaurantStatus_ACCEPTED(Long rest_id) {
		return repository.updateRestaurantStatus_ACCEPTED(rest_id);
	}

	@Override
	public int updateRestaurantStatus_DECLINED(Long rest_id) {
		return repository.updateRestaurantStatus_DECLINED(rest_id);
	}
}
