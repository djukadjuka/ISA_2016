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

import com.example.domain.RestaurantBean;
import com.example.domain.RestaurantZonesRelation;
import com.example.service.ZoneService;
import com.example.service.ZoneServiceBean;

@RestController
public class ZoneController {

	@Autowired
	private ZoneService zoneService = new ZoneServiceBean();
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value = "/getAllZones",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<ArrayList<RestaurantZonesRelation>> getRestaurant(){
		ArrayList<RestaurantZonesRelation> allRestaurants = (ArrayList<RestaurantZonesRelation>) zoneService.findAll();
		
		return new ResponseEntity<ArrayList<RestaurantZonesRelation>>(allRestaurants,HttpStatus.OK);
	}
	
}
