package com.example.service.orderServices;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.orderBeans.RestaurantOrderItem;
import com.example.repository.order.RestaurantOrderItemRepository;


@Service
public class RestaurantOrderServiceBean implements RestaurantOrderItemService {
	
	

	@Autowired
	private RestaurantOrderItemRepository repository;
	

	@Override
	public RestaurantOrderItem create(RestaurantOrderItem item) {
		// TODO Auto-generated method stub
		return this.repository.save(item);
	}


	@Override
	public Collection<RestaurantOrderItem> getAllTypeProd(String prod_type) {
		// TODO Auto-generated method stub
		return this.repository.getAllTypeProd(prod_type);
	}

}
