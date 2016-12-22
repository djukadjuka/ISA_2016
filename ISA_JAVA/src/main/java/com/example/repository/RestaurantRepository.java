package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.RestaurantBean;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantBean, Long> {

}
