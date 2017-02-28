package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.orderBeans.RestaurantOrderBean;
import com.example.repository.OrderRepository;

@Service
public class OrderServiceBean implements OrderService {
	
	@Autowired
	private OrderRepository repository;

	@Override
	public void updateOrder(float price, String name, Long table, Long waiter) {
		// TODO Auto-generated method stub
		 this.repository.updateOrder(price, name, table, waiter);
	}

	@Override
	public RestaurantOrderBean create(RestaurantOrderBean res_order) {
		// TODO Auto-generated method stub
		return this.repository.save(res_order);
	}
	
	
	}


