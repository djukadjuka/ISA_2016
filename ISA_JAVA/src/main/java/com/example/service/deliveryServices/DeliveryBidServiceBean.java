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

	@Override
	public DeliveryOrderBid checkIfDeliveryOrderExists(Long user_id, Long order_id) {
		return this.repository.checkIfDeliveryOrderExists(user_id, order_id);
	}

	@Override
	public void updateCashForDeliveryBid(Long price, Long user_id, Long order_id) {
		this.repository.updateCashForDeliveryBid(price, user_id, order_id);
	}

	@Override
	public void updateNewBidInformation(Long price, Long user_id, Long order_id, Long restaurant_id, Long bid_id) {
		this.repository.updateNewBidInformation(price, user_id, order_id, restaurant_id, bid_id);
	}

	@Override
	public void setBidAccepted(Long bid_id) {
		this.repository.setBidAccepted(bid_id);
	}

	@Override
	public void setOtherBidsDeclined(Long order_id) {
		this.repository.setOtherBidsDeclined(order_id);
	}

	@Override
	public void setSeenStatus(Long id) {
		this.repository.setSeenStatus(id);
	}
	
	
}
