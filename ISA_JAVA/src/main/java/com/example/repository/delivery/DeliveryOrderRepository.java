package com.example.repository.delivery;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.domain.deliveryBeans.DeliveryOrderBean;
import com.example.domain.deliveryBeans.DeliveryOrderItem;

@Repository
public interface DeliveryOrderRepository extends JpaRepository<DeliveryOrderBean, Long>{
	
	/**
	 * GET all deliveries for a single restaurant
	 * */
	@Query(value="select del.* from"
			+ " delivery_order del, restaurant r"
			+ " where del.for_restaurant_id = r.id"
			+ " and r.id = :rest_id",nativeQuery=true)
	public Collection<DeliveryOrderBean> getDeliveryOrdersForRestaurant(@Param("rest_id") Long rest_id);

	/**
	 * get all deliveries that are pending
	 * */
	@Query(value = "select * from delivery_order d"
			+ " where d.accepted_user_id is null"
			+ " and d.date_from < :current_date"
			+ " and d.date_to > :current_date",nativeQuery=true)
	public Collection<DeliveryOrderBean> getAllFreeDeliveries(@Param("current_date") Long current_date);
	
	/**
	 * get all accepted deliveries
	 */
	
	/**
	 * get all declined deliveries
	 */
	
}
