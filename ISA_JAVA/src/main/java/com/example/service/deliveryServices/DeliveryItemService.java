package com.example.service.deliveryServices;

import java.util.Collection;

import org.springframework.data.repository.query.Param;

import com.example.domain.deliveryBeans.DeliveryOrderItem;

public interface DeliveryItemService {

	DeliveryOrderItem findOne(Long id);
	
	Collection<DeliveryOrderItem> findAll();
	
	DeliveryOrderItem update(DeliveryOrderItem item);
	
	DeliveryOrderItem create(DeliveryOrderItem item);
	
	void delete(Long id);
	
	//custom
	public Collection<DeliveryOrderItem> getItemsForDelivery(Long ord_id);
}
