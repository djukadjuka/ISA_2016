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

}
