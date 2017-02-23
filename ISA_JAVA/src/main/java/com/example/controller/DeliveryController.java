package com.example.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.deliveryBeans.DeliveryOrderBean;
import com.example.domain.deliveryBeans.DeliveryOrderBid;
import com.example.service.deliveryServices.DelivererService;
import com.example.service.deliveryServices.DelivererServiceBean;
import com.example.service.deliveryServices.DeliveryBidService;
import com.example.service.deliveryServices.DeliveryBidServiceBean;
import com.example.service.deliveryServices.DeliveryItemService;
import com.example.service.deliveryServices.DeliveryItemServiceBean;
import com.example.service.deliveryServices.DeliveryOrderService;
import com.example.service.deliveryServices.DeliveryOrderServiceBean;

@RestController
public class DeliveryController {

	@Autowired
	private DelivererService deliverer_service = new DelivererServiceBean();
	
	@Autowired
	private DeliveryBidService delivery_bid_service = new DeliveryBidServiceBean();
	
	@Autowired 
	private DeliveryItemService delivery_item_service = new DeliveryItemServiceBean();
	
	@Autowired
	private DeliveryOrderService delivery_order_service = new DeliveryOrderServiceBean();
	
	//////////////////////////////////////////////////
	////////FOR MANAGER
	/////////////////////////////////////////////////
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value = "/delivery_controller/getStartingData/{rest_id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Collection<DeliveryOrderBean>> getEmployeesForRestaurant(@PathVariable("rest_id") Long rest_id){
		return new ResponseEntity<Collection<DeliveryOrderBean>>(this.delivery_order_service.getDeliveryOrdersForRestaurant(rest_id),HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value="/delivery_controller/getBidsForDeliveryId/{ord_id}",
			method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Collection<DeliveryOrderBid>> getBidsForOrder(@PathVariable("ord_id") Long ord_id){
		return new ResponseEntity<Collection<DeliveryOrderBid>>(this.delivery_bid_service.getDeliveryBidsForDeliveryId(ord_id),HttpStatus.OK);
	}
	
	///////////////////////////////////////////////////////////
	///////SPECIFIC FOR DELIVERER
	//////////////////////////////////////////////////////////
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value="/delivery_controller/getFreeDeliveries/current_date/{current_date}/emp_id/{emp_id}",
			method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<HashMap<String,Object>> getFreeBids(@PathVariable("current_date") Long current_date,
																	 @PathVariable("emp_id") Long emp_id){
		
		HashMap<String,Object> payload = new HashMap<String,Object>();
		ArrayList<DeliveryOrderBid> status_bids = (ArrayList<DeliveryOrderBid>) this.delivery_bid_service.getNotSeenDeliveryStatuses(emp_id);
		ArrayList<DeliveryOrderBean> free_orders = (ArrayList<DeliveryOrderBean>) this.delivery_order_service.getAllFreeDeliveries(current_date);
		
		for(int idx=0;	idx<status_bids.size();	idx++){
			Long date_to = status_bids.get(idx).getMade_for_order().getDate_to();
			if(date_to < current_date){
				this.delivery_bid_service.setBidToBeExpired(status_bids.get(idx).getId());
				status_bids.get(idx).setBid_status(null);
			}
		}
		
		ArrayList<DeliveryOrderBid> all_bids = (ArrayList<DeliveryOrderBid>) this.delivery_bid_service.getAllPossibleDeliveryBids(emp_id);
		payload.put("status_bids", status_bids);
		payload.put("free_orders", free_orders);
		payload.put("all_bids", all_bids);
		
		return new ResponseEntity<HashMap<String,Object>>(payload,HttpStatus.OK);
	}
	
}
