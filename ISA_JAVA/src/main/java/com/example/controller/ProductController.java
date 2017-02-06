package com.example.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.ProductBean;
import com.example.service.ProductService;
import com.example.service.ProductServiceBean;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService = new ProductServiceBean();
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value="/getAllProducts",
			method = RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<ArrayList<ProductBean>> getAllProducts(){
		ArrayList<ProductBean> allProducts= (ArrayList<ProductBean>) productService.findAll();
		
		return new ResponseEntity<ArrayList<ProductBean>>(allProducts,HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value = "/getAllFood",
			method = RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<ArrayList<ProductBean>> getAllFood(){
		ArrayList<ProductBean> allProds = (ArrayList<ProductBean>) productService.findAll();
		
		ArrayList<ProductBean> allFood = new ArrayList<>();
		
		for(ProductBean p : allProds){
			if(p.isFood())
				allFood.add(p);
		}
		
		return new ResponseEntity<ArrayList<ProductBean>>(allFood,HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value = "/getAllDrinks",
			method = RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<ArrayList<ProductBean>> getAllDrinks(){
		ArrayList<ProductBean> allProds= (ArrayList<ProductBean>) productService.findAll();
		ArrayList<ProductBean> allDrinks = new ArrayList<>();
				
		for(ProductBean p : allProds){
			if(!p.isFood())
				allDrinks.add(p);
		}
		return new ResponseEntity<ArrayList<ProductBean>>(allDrinks,HttpStatus.OK);
	}
	
	
}
