package com.example.service.deliveryServices;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.deliveryBeans.DeliveryOrderBean;
import com.example.repository.delivery.DeliveryOrderRepository;

@Service
public class DeliveryOrderServiceBean implements DeliveryOrderService{

	@Autowired
	private DeliveryOrderRepository repository;
	
	@Override
	public DeliveryOrderBean findOne(Long id) {
		return this.repository.findOne(id);
	}

	@Override
	public Collection<DeliveryOrderBean> findAll() {
		return this.repository.findAll();
	}

	@Override
	public DeliveryOrderBean update(DeliveryOrderBean del_order) {
		return this.repository.save(del_order);
	}

	@Override
	public DeliveryOrderBean create(DeliveryOrderBean del_order) {
		return this.repository.save(del_order);
	}

	@Override
	public void delete(Long id) {
		this.repository.delete(id);
	}

	@Override
	public Collection<DeliveryOrderBean> getDeliveryOrdersForRestaurant(Long rest_id) {
		return this.repository.getDeliveryOrdersForRestaurant(rest_id);
	}

}
