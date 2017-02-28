package com.example.service.orderServices;

import java.util.Collection;

import com.example.domain.ProductBean;
import com.example.domain.orderBeans.RestaurantOrderItem;

public interface RestaurantOrderItemService {

	Collection<RestaurantOrderItem> getAllTypeProd(String prod_type);
	
	RestaurantOrderItem create(RestaurantOrderItem item);
}
