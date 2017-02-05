package com.example.service;

import java.util.Collection;

import com.example.domain.ProductBean;

public interface ProductService {

	Collection<ProductBean> findAll();
	
	ProductBean findOne(Long id);
	
	ProductBean create(ProductBean product);
	
	ProductBean update(ProductBean product);
	
	void delete(Long id);
	
}
