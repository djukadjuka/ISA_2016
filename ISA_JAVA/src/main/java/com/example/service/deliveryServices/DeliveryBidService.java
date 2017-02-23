package com.example.service.deliveryServices;

import java.util.Collection;

import org.springframework.data.repository.query.Param;

import com.example.domain.deliveryBeans.DeliveryOrderBid;

public interface DeliveryBidService {

	DeliveryOrderBid findOne(Long id);
	
	Collection<DeliveryOrderBid> findAll();
	
	DeliveryOrderBid update(DeliveryOrderBid bid);
	
	DeliveryOrderBid create(DeliveryOrderBid bid);
	
	void delete(Long id);
	
	//custom
	public Collection<DeliveryOrderBid> getDeliveryBidsForDeliveryId(Long ord_id);
}
