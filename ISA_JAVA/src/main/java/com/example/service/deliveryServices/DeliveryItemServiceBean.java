package com.example.service.deliveryServices;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.deliveryBeans.DeliveryOrderItem;
import com.example.repository.delivery.DeliveryItemRepository;

@Service
public class DeliveryItemServiceBean implements DeliveryItemService{

	@Autowired
	private DeliveryItemRepository repository;
	
	@Override
	public DeliveryOrderItem findOne(Long id) {
		return this.repository.findOne(id);
	}

	@Override
	public Collection<DeliveryOrderItem> findAll() {
		return this.repository.findAll();
	}

	@Override
	public DeliveryOrderItem update(DeliveryOrderItem item) {
		return this.repository.save(item);
	}

	@Override
	public DeliveryOrderItem create(DeliveryOrderItem item) {
		return this.repository.save(item);
	}

	@Override
	public void delete(Long id) {
		this.repository.delete(id);
	}

	@Override
	public Collection<DeliveryOrderItem> getItemsForDelivery(Long ord_id) {
		return this.repository.getItemsForDelivery(ord_id);
	}

}
