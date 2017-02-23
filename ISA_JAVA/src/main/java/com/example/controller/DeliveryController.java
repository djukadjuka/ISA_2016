package com.example.controller;

import java.util.Collection;

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
	
}
