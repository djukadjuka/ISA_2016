package com.example.service;

import java.util.Collection;

import com.example.domain.OrderBean;

public interface OrderService {

	
	
	public Collection<OrderBean> updateOrder(float price,String name ,Long table,Long waiter );
	
}
