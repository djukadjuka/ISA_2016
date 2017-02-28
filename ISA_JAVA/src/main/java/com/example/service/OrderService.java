package com.example.service;

import com.example.domain.orderBeans.RestaurantOrderBean;

public interface OrderService {

	RestaurantOrderBean create(RestaurantOrderBean res_order);
	
	public void updateOrder(float price,String name ,Long table,Long waiter );
	
}
