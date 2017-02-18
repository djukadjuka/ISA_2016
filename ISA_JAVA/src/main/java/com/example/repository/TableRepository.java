package com.example.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.domain.TableBean;


@Repository
public interface TableRepository extends JpaRepository<TableBean, Long> {
	
	@Query(value = "SELECT * FROM Restaurant_table t WHERE t.restaurant_zone_id = :zone_id", nativeQuery = true)
    public Collection<TableBean> findAllTablesByZoneId(@Param("zone_id") Long zone_id);

}
