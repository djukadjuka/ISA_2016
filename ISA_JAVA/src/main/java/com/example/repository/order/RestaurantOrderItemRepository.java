package com.example.repository.order;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.domain.deliveryBeans.DeliveryOrderBid;
import com.example.domain.orderBeans.RestaurantOrderItem;


@Repository
public interface RestaurantOrderItemRepository extends JpaRepository<RestaurantOrderItem, Long> {

	
	
	@Query(value="SELECT ri.id , ri.item_name,ri.item_price,ri.item_type,ri.order_item_status,ri.belongs_to_order "
			+ "FROM restaurant_order_item ri ,restaurant_order ro "
			+ "WHERE ri.belongs_to_order=ro.id "
			+ "and ri.item_type=:prod_type "
			,nativeQuery=true)
	public Collection<RestaurantOrderItem> getAllTypeProd(@Param("prod_type") String prod_type);
}
