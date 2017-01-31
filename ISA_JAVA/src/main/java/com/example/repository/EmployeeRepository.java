package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.EmployeeBean;

public interface EmployeeRepository extends JpaRepository<EmployeeBean, Long> {

}
