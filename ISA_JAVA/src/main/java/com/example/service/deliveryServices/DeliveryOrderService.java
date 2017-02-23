package com.example.service.deliveryServices;

import java.util.Collection;

import org.springframework.data.repository.query.Param;

import com.example.domain.deliveryBeans.DeliveryOrderBean;

public interface DeliveryOrderService {

	
	DeliveryOrderBean findOne(Long id);
	
	Collection<DeliveryOrderBean> findAll();
	
	DeliveryOrderBean update(DeliveryOrderBean del_order);
	
	DeliveryOrderBean create(DeliveryOrderBean del_order);
	
	void delete(Long id);
	
	//custom
	public Collection<DeliveryOrderBean> getDeliveryOrdersForRestaurant(Long rest_id);
}
