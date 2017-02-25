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

	/**
	 * GET all deliveries that are free to bid on
	 */
	@Override
	public Collection<DeliveryOrderBean> getAllFreeDeliveries(Long current_date) {
		return this.repository.getAllFreeDeliveries(current_date);
	}

	@Override
	public void setDeliveryOrderAccepted(Long user_id, Long price, Long order_id) {
		this.repository.setDeliveryOrderAccepted(user_id, price, order_id);
	}

}
