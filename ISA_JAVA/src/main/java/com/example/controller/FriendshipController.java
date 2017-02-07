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
import com.example.service.FriendshipService;
import com.example.service.FriendshipServiceBean;

@RestController
public class FriendshipController {

	@Autowired
	private FriendshipService friendshipService = new FriendshipServiceBean();
	
//	@CrossOrigin(origins = "http://localhost:4200")
//	@RequestMapping(
//			value="/getAllUsers/{id}",
//			method = RequestMethod.GET,
//			produces=MediaType.APPLICATION_JSON_VALUE
//			)
//	public ResponseEntity<ArrayList<ProductBean>> getAllProducts(){
//		ArrayList<ProductBean> allProducts= (ArrayList<ProductBean>) productService.findAll();
//		
//		return new ResponseEntity<ArrayList<ProductBean>>(allProducts,HttpStatus.OK);
//	}
}
