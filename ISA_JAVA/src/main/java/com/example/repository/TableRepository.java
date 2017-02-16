package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.TableBean;


@Repository
public interface TableRepository extends JpaRepository<TableBean, Long> {
	
	
	

}
