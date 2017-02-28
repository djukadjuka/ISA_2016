package com.example.repository;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.domain.OrderBean;

@Repository
public interface OrderRepository extends JpaRepository<OrderBean, Long> {

	@Transactional
	@Modifying
	@Query(value="INSERT INTO restaurant_order (id,price,name,type,from_table,served_by,cook_notification,waiter_notification)"
			+ " VALUES (1,:price,:name,null,:table_id,:waiter_id,1,1)" ,nativeQuery=true)
	public void updateOrder(@Param("price") float price,
			@Param("name") String name, 
			@Param("table_id") Long table_id ,
			@Param("waiter_id") Long waiter_id );
	
	
	
	
	
	
}
