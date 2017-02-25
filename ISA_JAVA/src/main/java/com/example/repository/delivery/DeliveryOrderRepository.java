package com.example.repository.delivery;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.deliveryBeans.DeliveryOrderBean;

@Repository
public interface DeliveryOrderRepository extends JpaRepository<DeliveryOrderBean, Long>{
	
	/**
	 * GET all deliveries for a single restaurant
	 * */
	@Query(value="select del.* from"
			+ " delivery_order del, restaurant r"
			+ " where del.for_restaurant_id = r.id"
			+ " and r.id = :rest_id and accepted_user_id is null",nativeQuery=true)
	public Collection<DeliveryOrderBean> getDeliveryOrdersForRestaurant(@Param("rest_id") Long rest_id);

	/**
	 * get all deliveries that are pending
	 * */
	@Query(value = "select * from delivery_order d"
			+ " where d.accepted_user_id is null"
			+ " and d.date_from < :current_date"
			+ " and d.date_to > :current_date",nativeQuery=true)
	public Collection<DeliveryOrderBean> getAllFreeDeliveries(@Param("current_date") Long current_date);
	
	/*@Transactional
	@Modifying
	@Query(value = "insert into delivery_order(id,date_from,date_to,made_by,for_restaurant_id,accepted_user_id,price_accepted)"
					+" values(1,969660000000,969746400000,1,1,17,null)" ,nativeQuery=true)
	public void createNewDeliveryCustom();*/
}
