package com.example.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.RestaurantBean;
import com.example.service.RestaurantService;
import com.example.service.RestaurantServiceBean;

@RestController
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService = new RestaurantServiceBean();
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value = "/getRestaurant/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<RestaurantBean> getRestaurant(@PathVariable("id") Long id){
		RestaurantBean restaurant = restaurantService.findOne(id);
		if(restaurant == null){
			return new ResponseEntity<RestaurantBean>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<RestaurantBean>(restaurant,HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value="/getAllRestaurants",
			method = RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<ArrayList<RestaurantBean>> getAllRestaurants(){
		ArrayList<RestaurantBean> allRestaurants = (ArrayList<RestaurantBean>) restaurantService.findAll();
		
		return new ResponseEntity<ArrayList<RestaurantBean>>(allRestaurants,HttpStatus.OK);
	}
	
	/*
	 * 	Hibernate: update restaurant_bean set name=?, type=? where id=?
	 *	Hibernate: delete from isa_database.restaurant_bean_drinks_menu where rest_id=?
	 *	Hibernate: delete from isa_database.restaurant_bean_food_menu where rest_id=?
	 * 
	 */
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value = "/updateRestaurant",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	@ResponseBody
	public ResponseEntity<RestaurantBean> updateRestaurant(@RequestBody RestaurantBean restaurant){
		System.out.println(restaurant);
		RestaurantBean r = restaurantService.findOne((long) restaurant.getId());
		System.out.println(r);
		if(r == null){
			return new ResponseEntity<RestaurantBean>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		r.setName(restaurant.getName());
		r.setType(restaurant.getType());
		restaurantService.update(r);
		
		
		return new ResponseEntity<RestaurantBean>(r,HttpStatus.OK);
	}
	
	
}
