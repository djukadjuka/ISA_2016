package com.example.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.RestaurantZoneBean;
import com.example.repository.ZoneRepository;

@Service
public class ZoneServiceBean implements ZoneService{

	@Autowired
	private ZoneRepository repository;
	
	@Override
	public RestaurantZoneBean findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public Collection<RestaurantZoneBean> findByRestaurant_id(Long restaurant_id) {
		return repository.findByRestaurant_id(restaurant_id);
	}

	@Override
	public Collection<RestaurantZoneBean> findAll() {
		return repository.findAll();
	}

	@Override
	public RestaurantZoneBean update(RestaurantZoneBean zone) {
		return repository.save(zone);
	}

	@Override
	public RestaurantZoneBean create(RestaurantZoneBean zone) {
		return repository.save(zone);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

}
