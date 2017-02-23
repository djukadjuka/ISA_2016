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

	/**
	 * DELIVERY BID NOTIFICATIONS THAT ARE NOT SEEN BY THE DELIVERER
	 */
	@Override
	public Collection<DeliveryOrderBid> getNotSeenDeliveryStatuses(Long emp_id) {
		return this.repository.getNotSeenDeliveryStatuses(emp_id);
	}

	/**
	 * GET ALL POSSIBLE BIDS THIS DELIVERER MADE EVER
	 */
	@Override
	public Collection<DeliveryOrderBid> getAllPossibleDeliveryBids(Long emp_id) {
		return this.repository.getAllPossibleDeliveryBids(emp_id);
	}

	@Override
	public Collection<DeliveryOrderBid> getDeliveryBidsForDeliveryId(Long ord_id) {
		return this.repository.getDeliveryBidsForDeliveryId(ord_id);
	}

	@Override
	public void setBidToBeExpired(Long dob_id) {
		this.repository.setBidToBeExpired(dob_id);
	}
	
	
}
