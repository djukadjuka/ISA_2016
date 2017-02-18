package com.example.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.TableBean;
import com.example.repository.TableRepository;

@Service
public class TableServiceBean implements TableService{
	
	@Autowired
	private TableRepository repository;

	@Override
	public Collection<TableBean> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Collection<TableBean> findAllTablesByZoneId(Long zone_id) {
		// TODO Auto-generated method stub
		return repository.findAllTablesByZoneId(zone_id);
	}

}
