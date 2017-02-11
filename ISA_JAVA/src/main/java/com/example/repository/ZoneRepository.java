package com.example.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.domain.RestaurantZoneBean;

@Repository
public interface ZoneRepository extends JpaRepository<RestaurantZoneBean, Long>{

	@Query(value = "SELECT * FROM restaurant_zone rz WHERE rz.restaurant_id = :restaurant_id", nativeQuery = true)
	public Collection<RestaurantZoneBean> findByRestaurant_id(@Param("restaurant_id") Long restaurant_id);
	
	
}
