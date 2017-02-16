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

}
