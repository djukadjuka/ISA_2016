package com.example.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.ReservationBean;
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

	public TableBean create(TableBean table){
		return this.repository.save(table);
	}
	
	@Override
	public Collection<TableBean> findAllTablesByZoneId(Long zone_id) {
		// TODO Auto-generated method stub
		return repository.findAllTablesByZoneId(zone_id);
	}

	@Override
	public TableBean findOne(Long id) {
		// TODO Auto-generated method stub
		return repository.findOne(id);
	}


	public Collection<TableBean> findAllTablesFromRestaurant(Long rest_id) {
		return repository.findAllTablesFromRestaurant(rest_id);
	}
	
	@Override
	public int updateTableStatus(Long id, String status){
		// TODO Auto-generated method stub
		return repository.updateTableStatus(id,status);
	}

	@Override
	public void update_served_by_employee(Long served_by, Long table_id) {
		this.repository.update_served_by_employee(served_by, table_id);
	}

}
