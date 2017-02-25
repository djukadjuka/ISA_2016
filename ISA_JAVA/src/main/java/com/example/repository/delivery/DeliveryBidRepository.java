package com.example.repository.delivery;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.deliveryBeans.DeliveryOrderBid;

@Repository
public interface DeliveryBidRepository extends JpaRepository<DeliveryOrderBid, Long>{

	/**
	 * GET all bids for specific delivery
	 */
	@Query(value="select * from delivery_order_bid d"
			+ " where d.made_for_order_id = :ord_id",nativeQuery=true)	
	public Collection<DeliveryOrderBid> getDeliveryBidsForDeliveryId(@Param("ord_id") Long ord_id);
	
	/**
	 * get all bids that are not seen, and made by specific user
	 * FOR PRESENTING NOTIFICATIONS
	 */
	@Query(value="select * from delivery_order_bid dob"
			+ " where (dob.seen_status = 0 or dob.seen_status is null)"
			+ " and dob.made_by_deliverer_user_id = :emp_id",nativeQuery=true)
	public Collection<DeliveryOrderBid> getNotSeenDeliveryStatuses(@Param("emp_id") Long emp_id);
	
	/**
	 * FOR UPDATING SEEN STATUS
	 */
	@Transactional
	@Modifying
	@Query(value = "update delivery_order_bid dob"
			+ " set dob.seen_status = 1"
			+ " where dob.id = :dob_id",nativeQuery = true)
	public DeliveryOrderBid updateDeliveryBidSeen(@Param("dob_id") Long dob_id);
	
	/**
	 * SET ONE DELIVERY ACCEPTED
	 */
	@Transactional
	@Modifying
	@Query(value = "update delivery_order_bid dob"
			+ " set dob.seen_status = 0, dob.bid_status = 'ACCEPTED'"
			+ " where dob.id = :dob_id",nativeQuery = true)
	public DeliveryOrderBid updateDeliveryACCEPTED_AND_NOT_SEEN(@Param("dob_id") Long dob_id);
	
	/**
	 * SET OTHER DELIVERIES NOT ACCEPTED
	 * its the one made for the order id that is pending
	 */
	@Transactional
	@Modifying
	@Query(value = "update delivery_order_bid dob"
			+ " set dob.seen_status = 0, dob.bid_status = 'DECLINED'"
			+ " where dob.bid_status = 'PENDING' and made_for_order_id = :ord_id",nativeQuery=true)
	public int rejectOtherBids(@Param("ord_id") Long ord_id);
	
	/**
	 * get all possible bids that this deliverer made
	 * FOR PRESENTING ALL BIDS FROM ONE DELIVERER
	 */
	@Query(value = "select * from delivery_order_bid dob"
			+ "	where dob.made_by_deliverer_user_id = :emp_id",nativeQuery=true)
	public Collection<DeliveryOrderBid> getAllPossibleDeliveryBids(@Param("emp_id") Long emp_id);
	
	/**
	 * Places a null value to the bids status so that it is expired
	 * */
	@Transactional
	@Modifying
	@Query(value = "update delivery_order_bid dob"
			+ " set dob.bid_status = null"
			+ " where dob.id = :dob_id",nativeQuery=true)
	public void setBidToBeExpired(@Param("dob_id") Long dob_id);
	
	/**check if bid exists*/
	@Query(value = "select * from delivery_order_bid"
			+ " where made_by_deliverer_user_id = :user_id and made_for_order_id = :order_id", nativeQuery=true)
	public DeliveryOrderBid checkIfDeliveryOrderExists(@Param("user_id") Long user_id, @Param("order_id") Long order_id);
	
	/**Update existing bid*/
	@Transactional
	@Modifying
	@Query(value = "update delivery_order_bid set bidding_price = :price"
			+ " where made_by_deliverer_user_id = :user_id and made_for_order_id = :order_id",nativeQuery=true)
	public void updateCashForDeliveryBid(@Param("price") Long price,@Param("user_id") Long user_id, @Param("order_id") Long order_id);
	
	/**update new bid*/
	@Transactional
	@Modifying
	@Query(value = "update delivery_order_bid set "
			+ " bid_status='PENDING', bidding_price=:price,seen_status=0,made_by_deliverer_user_id=:user_id"
			+ " ,made_for_order_id=:order_id,made_for_restaurant_id=:restaurant_id"
			+ " where id=:bid_id",nativeQuery=true)
	public void updateNewBidInformation(
			@Param("price") Long price,
			@Param("user_id") Long user_id,
			@Param("order_id") Long order_id,
			@Param("restaurant_id") Long restaurant_id,
			@Param("bid_id") Long bid_id
			);
}
