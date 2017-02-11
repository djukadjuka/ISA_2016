package com.example.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.RestaurantZonesRelation;
import com.example.repository.ZoneRepository;

@Service
public class ZoneServiceBean implements ZoneService{

	@Autowired
	private ZoneRepository repository;
	
	@Override
	public Collection<RestaurantZonesRelation> findAll() {
		return repository.findAll();
	}

	@Override
	public RestaurantZonesRelation findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public RestaurantZonesRelation create(RestaurantZonesRelation zoneRelation) {
		RestaurantZonesRelation relation = repository.save(zoneRelation);
		return relation;
	}

	@Override
	public RestaurantZonesRelation update(RestaurantZonesRelation zoneRelation) {
		RestaurantZonesRelation relation = repository.findOne(zoneRelation.getId());
		if(relation == null){
			return null;
		}
		relation = repository.save(zoneRelation);
		return relation;
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

}
