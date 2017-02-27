package com.example.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.OrderBean;
import com.example.repository.OrderRepository;
import com.example.repository.ReservationCallRepository;

@Service
public class OrderServiceBean implements OrderService {
	
	@Autowired
	private OrderRepository repository;

	@Override
	public Collection<OrderBean> updateOrder(float price, String name, Long table, Long waiter) {
		// TODO Auto-generated method stub
		return repository.updateOrder(price, name, table, waiter);
	}
	
	
	}


