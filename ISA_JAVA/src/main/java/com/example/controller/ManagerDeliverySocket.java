package com.example.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.example.domain.deliveryBeans.DeliveryOrderBean;
import com.example.service.EmployeeService;
import com.example.service.EmployeeServiceBean;
import com.example.service.RestaurantService;
import com.example.service.RestaurantServiceBean;
import com.example.service.deliveryServices.DelivererService;
import com.example.service.deliveryServices.DelivererServiceBean;
import com.example.service.deliveryServices.DeliveryBidService;
import com.example.service.deliveryServices.DeliveryBidServiceBean;
import com.example.service.deliveryServices.DeliveryItemService;
import com.example.service.deliveryServices.DeliveryItemServiceBean;
import com.example.service.deliveryServices.DeliveryOrderService;
import com.example.service.deliveryServices.DeliveryOrderServiceBean;

@Controller
public class ManagerDeliverySocket {

	//=========================================================
	//========================SERVICES
	//=========================================================
	@Autowired
	private EmployeeService employee_service = new EmployeeServiceBean();
	
	@Autowired
	private RestaurantService restaurant_serivec = new RestaurantServiceBean();
	
	@Autowired
	private DelivererService deliverer_service = new DelivererServiceBean();
	
	@Autowired
	private DeliveryBidService delivery_bid_service = new DeliveryBidServiceBean();
	
	@Autowired 
	private DeliveryItemService delivery_item_service = new DeliveryItemServiceBean();
	
	@Autowired
	private DeliveryOrderService delivery_order_service = new DeliveryOrderServiceBean();
	
	@MessageMapping("/testDeliveries/{id}")
	@SendTo("/deliveries/getDeliveryStartingData/{id}")
	public Collection<DeliveryOrderBean> testFunkcija(@DestinationVariable Long id){
		
		return this.delivery_order_service.getDeliveryOrdersForRestaurant(id);
	}
	//return new ResponseEntity<Collection<DeliveryOrderBean>>(this.delivery_order_service.getDeliveryOrdersForRestaurant(rest_id),HttpStatus.OK);
}
