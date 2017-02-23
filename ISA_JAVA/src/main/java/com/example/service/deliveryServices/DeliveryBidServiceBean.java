package com.example.service.deliveryServices;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.deliveryBeans.DeliveryOrderBid;
import com.example.repository.delivery.DeliveryBidRepository;

@Service
public class DeliveryBidServiceBean implements DeliveryBidService{

	@Autowired
	private DeliveryBidRepository repository;

	@Override
	public DeliveryOrderBid findOne(Long id) {
		return this.repository.findOne(id);
	}

	@Override
	public Collection<DeliveryOrderBid> findAll() {
		return this.repository.findAll();
	}

	@Override
	public DeliveryOrderBid update(DeliveryOrderBid bid) {
		return this.repository.save(bid);
	}

	@Override
	public DeliveryOrderBid create(DeliveryOrderBid bid) {
		return this.repository.save(bid);
	}

	@Override
	public void delete(Long id) {
		this.repository.delete(id);
	}

	@Override
	public Collection<DeliveryOrderBid> getDeliveryBidsForDeliveryId(Long ord_id) {
		return this.repository.getDeliveryBidsForDeliveryId(ord_id);
	}
	
	
}
