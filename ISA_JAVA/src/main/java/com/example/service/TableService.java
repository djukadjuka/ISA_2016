package com.example.service;

import java.util.Collection;

import org.springframework.data.repository.query.Param;

import com.example.domain.TableBean;

public interface TableService {

	Collection<TableBean> findAll();
	
	TableBean findOne(Long id);
	
	int updateTableStatus(Long id, String status);
	
	Collection<TableBean> findAllTablesByZoneId(Long zone_id);
	
	//DJ_CUSTOM
	public Collection<TableBean> findAllTablesFromRestaurant(Long rest_id);
}
