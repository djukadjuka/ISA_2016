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

import com.example.domain.RestaurantZoneBean;
import com.example.service.ZoneService;
import com.example.service.ZoneServiceBean;

@RestController
public class ZoneController {

	@Autowired
	private ZoneService zoneService = new ZoneServiceBean();
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value = "/getZoneByRestaurantId/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<ArrayList<RestaurantZoneBean>> getZoneByRestaurantId(@PathVariable("id") Long id){
		ArrayList<RestaurantZoneBean> zones = (ArrayList<RestaurantZoneBean>) zoneService.findByRestaurant_id(id);
		
		return new ResponseEntity<ArrayList<RestaurantZoneBean>>(zones,HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value = "/getAllZones",
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<ArrayList<RestaurantZoneBean>> getAllZones(){
		ArrayList<RestaurantZoneBean> zones = (ArrayList<RestaurantZoneBean>) zoneService.findAll();
		
		return new ResponseEntity<ArrayList<RestaurantZoneBean>>(zones, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value="/editZone",
			method=RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	@ResponseBody
	public ResponseEntity<RestaurantZoneBean> editZone(@RequestBody RestaurantZoneBean zone){
		RestaurantZoneBean old_zone = zoneService.findOne(zone.getId());
		
		old_zone.setDeleted(zone.getDeleted());
		old_zone.setName(zone.getName());
		old_zone.setNumber_of_tables(zone.getNumber_of_tables());
		old_zone.setRestaurant(zone.getRestaurant());
		old_zone.setTables_for_x(zone.getTables_for_x());
		
		zoneService.update(old_zone);
		
		return new ResponseEntity<RestaurantZoneBean>(old_zone,HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value="/addZone",
			method=RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	@ResponseBody
	public ResponseEntity<ArrayList<RestaurantZoneBean>> addZone(@RequestBody RestaurantZoneBean zone){
		zoneService.create(zone);
		
		ArrayList<RestaurantZoneBean> zones = (ArrayList<RestaurantZoneBean>) zoneService.findByRestaurant_id(zone.getRestaurant().getId());
		return new ResponseEntity<ArrayList<RestaurantZoneBean>>(zones,HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value="/deleteZone/{id}",
			method=RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	@ResponseBody
	public ResponseEntity<ArrayList<RestaurantZoneBean>> deleteZone(@PathVariable("id") Long id){
		RestaurantZoneBean zone = zoneService.findOne(id);
		zone.setDeleted(1);
		zoneService.update(zone);
		
		ArrayList<RestaurantZoneBean> zones = (ArrayList<RestaurantZoneBean>) zoneService.findByRestaurant_id(zone.getRestaurant().getId());
		return new ResponseEntity<ArrayList<RestaurantZoneBean>>(zones,HttpStatus.OK);
	}
}
