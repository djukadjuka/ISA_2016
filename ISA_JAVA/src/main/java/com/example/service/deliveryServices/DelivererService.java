package com.example.service.deliveryServices;

import java.util.Collection;

import com.example.domain.deliveryBeans.DelivererBean;

public interface DelivererService {
	
	DelivererBean findOne(Long id);
	
	Collection<DelivererBean> findAll();
	
	DelivererBean update(DelivererBean deliverer);
	
	DelivererBean create(DelivererBean deliverer);
	
	void delete(Long id);
	
}
