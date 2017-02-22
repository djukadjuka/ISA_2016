package com.example.service;

import java.util.Collection;

import com.example.domain.TableBean;

public interface TableService {

	Collection<TableBean> findAll();
	
	TableBean findOne(Long id);
	
	int updateTableStatus(TableBean t);
	
	Collection<TableBean> findAllTablesByZoneId(Long zone_id);
}
