package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.OrderBean;
import com.example.service.OrderService;
import com.example.service.OrderServiceBean;


@RestController
public class OrderController {
	
	@Autowired
	private OrderService orderService = new OrderServiceBean();

	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value="order/updateOrder/{food_name}/{food_price}/{table_id}/{user_id}",
					method = RequestMethod.POST,
					produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public synchronized void updateOrder(@PathVariable("food_name") String name, 
			@PathVariable("food_price") float price ,
			@PathVariable("table_id") Long table
			,@PathVariable("user_id") Long waiter){
		this.orderService.updateOrder(price, name, table, waiter);
	}
	
	
	
}
