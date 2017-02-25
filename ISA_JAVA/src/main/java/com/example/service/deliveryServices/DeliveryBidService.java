package com.example.service.deliveryServices;

import java.util.Collection;

import com.example.domain.deliveryBeans.DeliveryOrderBid;

public interface DeliveryBidService {

	DeliveryOrderBid findOne(Long id);
	
	Collection<DeliveryOrderBid> findAll();
	
	DeliveryOrderBid update(DeliveryOrderBid bid);
	
	DeliveryOrderBid create(DeliveryOrderBid bid);
	
	void delete(Long id);
	
	//custom
	/**DELIVERY BID NOTIFICATIONS THAT ARE NOT SEEN BY THE DELIVERER*/
	public Collection<DeliveryOrderBid> getNotSeenDeliveryStatuses(Long emp_id);
	
	/**GET ALL POSSIBLE BIDS THIS DELIVERER MADE EVER*/
	public Collection<DeliveryOrderBid> getAllPossibleDeliveryBids(Long emp_id);

	/**GET ALL BIDS FOR ONE DELIVERY*/
	Collection<DeliveryOrderBid> getDeliveryBidsForDeliveryId(Long ord_id);
	
	/**SETS THE BIDS STATUS TO NULL SO THAT IT IS EXPIRED IN THE FRONT END*/
	public void setBidToBeExpired(Long dob_id);
	
	public DeliveryOrderBid checkIfDeliveryOrderExists(Long user_id, Long order_id);
	public void updateCashForDeliveryBid(Long price,Long user_id, Long order_id);
	
	public void updateNewBidInformation(
			Long price,
			Long user_id,
			Long order_id,
			Long restaurant_id,
			Long bid_id
			);
}
