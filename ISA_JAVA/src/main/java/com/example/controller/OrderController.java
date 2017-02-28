package com.example.controller;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.orderBeans.RestaurantOrderBean;
import com.example.domain.orderBeans.RestaurantOrderItem;
import com.example.service.OrderService;
import com.example.service.OrderServiceBean;
import com.example.service.orderServices.RestaurantOrderItemService;
import com.example.service.orderServices.RestaurantOrderServiceBean;


@RestController
public class OrderController {
	
	public static Long oznaka = (long) 1 ; 
	
	@Autowired
	private OrderService orderService = new OrderServiceBean();
	
	@Autowired
	private RestaurantOrderItemService itemOrderService = new RestaurantOrderServiceBean();

	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value="order/updateOrder/{food_name}/{food_price}/{table_id}/{user_id}",
					method = RequestMethod.POST,
					produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public synchronized void updateOrder(@PathVariable("food_name") String name, 
			@PathVariable("food_price") float price ,
			@PathVariable("table_id") Long table
			,@PathVariable("user_id") Long waiter){
		oznaka++;
		this.orderService.updateOrder(price, name, table, waiter);
	}
	
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value = "/order_controller/sendNewOrder",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE
			)
	@ResponseBody
	public synchronized void createNewOrder(@RequestBody RequestOrderWrapper wrapper){
		RestaurantOrderBean order = wrapper.getOrder();
		order.setTable_id(wrapper.getRest_id());
		order.setWaiter_id(wrapper.getUser_id());
		HashSet<RestaurantOrderItem> items = (HashSet<RestaurantOrderItem>) order.getContains_items();
		
		order.setContains_items(null);
		order.setId(this.orderService.create(order).getId());
		for(RestaurantOrderItem item : items){
			item.setBelongs_to_order(order);
			this.itemOrderService.create(item);
		
		}
		
		System.out.println(order.getTable_id());
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value="/getAllTypeProd/{prod_type}",
			method = RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	
		public ResponseEntity<Collection<RestaurantOrderItem>> getAllTypeProd(@PathVariable("prod_type") String prod_type){
		
		return new ResponseEntity<Collection<RestaurantOrderItem>>(itemOrderService.getAllTypeProd(prod_type),HttpStatus.OK);
	}
	
	
}



class RequestOrderWrapper{
	private Long rest_id;
	private Long user_id;
	private RestaurantOrderBean order;
	
	
	public RestaurantOrderBean getOrder() {
		return order;
	}
	public void setOrder(RestaurantOrderBean order) {
		this.order = order;
	}
	


public Long getRest_id() {
	return rest_id;
}
public void setRest_id(Long rest_id) {
	this.rest_id = rest_id;
}
public Long getUser_id() {
	return user_id;
}
public void setUser_id(Long user_id) {
	this.user_id = user_id;
}

}

