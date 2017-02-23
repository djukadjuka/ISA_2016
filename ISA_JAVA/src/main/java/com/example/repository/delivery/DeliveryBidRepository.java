package com.example.repository.delivery;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.domain.deliveryBeans.DeliveryOrderBid;

@Repository
public interface DeliveryBidRepository extends JpaRepository<DeliveryOrderBid, Long>{

	/**
	 * GET all bids for specific delivery
	 */
	@Query(value="select * from delivery_order_bid d"
			+ " where d.made_for_order_id = :ord_id",nativeQuery=true)	
	public Collection<DeliveryOrderBid> getDeliveryBidsForDeliveryId(@Param("ord_id") Long ord_id);
}
