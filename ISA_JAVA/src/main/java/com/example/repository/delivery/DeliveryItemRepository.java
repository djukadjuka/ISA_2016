package com.example.repository.delivery;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.domain.deliveryBeans.DeliveryOrderItem;

@Repository
public interface DeliveryItemRepository extends JpaRepository<DeliveryOrderItem, Long>{


	/**
	 * GET all items that belong to an order
	 * */
	@Query(value = "select * from delivery_order_item doi"
			+ " where doi.belongs_to_order = :ord_id",nativeQuery=true)
	public Collection<DeliveryOrderItem> getItemsForDelivery(@Param("ord_id") Long ord_id);
}
