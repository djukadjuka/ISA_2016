package com.example.service;

import java.util.Collection;

import com.example.domain.TableBean;

public interface TableService {

	Collection<TableBean> findAll();
	
	
}