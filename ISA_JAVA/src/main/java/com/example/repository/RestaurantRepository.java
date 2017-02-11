package com.example.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.domain.RestaurantBean;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantBean, Long> {
	
	@Query(value = "SELECT * FROM Restaurant r WHERE r.name = :name", nativeQuery = true)
    public Collection<RestaurantBean> filterRestaurants(@Param("name") String name);
}
