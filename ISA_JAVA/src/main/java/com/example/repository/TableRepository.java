package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.TableBean;

public interface TableRepository extends JpaRepository<TableBean, Long> {
	
	
	

}
