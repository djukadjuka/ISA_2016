package com.example.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.RestaurantBean;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantBean, Long> {
	
	@Query(value = "SELECT * FROM Restaurant r WHERE r.name LIKE CONCAT('%',:name,'%')", nativeQuery = true)
    public Collection<RestaurantBean> filterRestaurantsByName(@Param("name") String name);
	
	@Query(value = "SELECT * FROM Restaurant r WHERE r.type = :type", nativeQuery = true)
    public Collection<RestaurantBean> filterRestaurantsByType(@Param("type") String type);
	
	@Query(value = "SELECT * FROM Restaurant r WHERE r.name LIKE CONCAT('%',:name,'%') and r.type = :type", nativeQuery = true)
    public Collection<RestaurantBean> filterRestaurantsByNameAndType(@Param("name") String name, @Param("type") String type);
	
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO manages_restaurants(manager_id,rest_id) values(:man_id,:rest_id)",nativeQuery=true)
	public void createManagerRelation(@Param("man_id") Long man_id, @Param("rest_id") Long rest_id);
}
