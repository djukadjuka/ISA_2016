package com.example.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.ProductBean;
import com.example.repository.ProductRepository;

@Service
public class ProductServiceBean implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public Collection<ProductBean> findAll() {
		return productRepository.findAll();
	}

	@Override
	public ProductBean findOne(Long id) {
		ProductBean product = productRepository.findOne(id);
		return product;
	}

	@Override
	public ProductBean create(ProductBean product) {
		ProductBean prod = productRepository.save(product);
		return prod;
	}

	@Override
	public ProductBean update(ProductBean product) {
		ProductBean prod = productRepository.getOne((long) product.getId());
		if(prod == null)
			return null;
		prod = productRepository.save(product);
		return prod;
	}

	@Override
	public void delete(Long id) {
		productRepository.delete(id);
	}
	
}
