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
import com.example.domain.TableBean;
import com.example.service.RestaurantService;
import com.example.service.RestaurantServiceBean;
import com.example.service.TableService;
import com.example.service.TableServiceBean;
import com.example.service.ZoneService;
import com.example.service.ZoneServiceBean;

@RestController
public class ZoneController {
	
	@Autowired
	private TableService table_service = new TableServiceBean();

	@Autowired
	private ZoneService zoneService = new ZoneServiceBean();
	
	@Autowired
	private RestaurantService restaurant_service = new RestaurantServiceBean();
	
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
			value="/editZoneFIX",
			method=RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	@ResponseBody
	public ResponseEntity<RestaurantZoneBean> editZoneFIX(@RequestBody RestaurantZoneBean zone){
		
		this.restaurant_service.delete_tables_from_zone(zone.getId());
		this.restaurant_service.delete_zone_from_restaurant(zone.getRestaurant().getId(), zone.getId());
		
		RestaurantZoneBean rzb = this.zoneService.create(zone);
		for(int i=0;	i<rzb.getNumber_of_tables();	i++){
			TableBean table = new TableBean();
			table.setMaxPeople(rzb.getTables_for_x());
			table.setStatus(TableBean.TableStatus.FREE);
			//table.setServed_by(null);
			table.setRestaurant_zone_id(rzb);
			
			this.table_service.create(table);
		}
		
		return new ResponseEntity<RestaurantZoneBean>(rzb,HttpStatus.OK);
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
		
		for(int i=0;	i<zone.getNumber_of_tables();	i++){
			TableBean table = new TableBean();
			table.setMaxPeople(zone.getTables_for_x());
			table.setStatus(TableBean.TableStatus.FREE);
			//table.setServed_by(null);
			table.setRestaurant_zone_id(zone);
			
			this.table_service.create(table);
		}
		
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
