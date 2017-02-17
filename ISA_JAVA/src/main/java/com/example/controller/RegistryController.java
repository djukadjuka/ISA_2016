package com.example.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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

import com.example.domain.EmployeeBean;
import com.example.domain.RestaurantBean;
import com.example.domain.RestaurantRegistry;
import com.example.service.EmployeeService;
import com.example.service.EmployeeServiceBean;
import com.example.service.RestaurantRegistryService;
import com.example.service.RestaurantRegistryServiceBean;
import com.example.service.RestaurantService;
import com.example.service.RestaurantServiceBean;

@RestController
public class RegistryController {

		@Autowired
		private RestaurantRegistryService service = new RestaurantRegistryServiceBean();
		
		@Autowired
		private RestaurantService restaurant_service = new RestaurantServiceBean();
		
		@Autowired
		private EmployeeService employee_service = new EmployeeServiceBean();
		
		private final String path_prefix = "/restaurantRegistry/";
		
		/**
		 * GET
		 * Manager hasn't seen update
		 * */
		@CrossOrigin(origins = "http://localhost:4200")
		@RequestMapping(value=path_prefix + "getUnseenByManager/{mgr_id}",
						method=RequestMethod.GET,
						produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<ArrayList<RestaurantRegistry>> getUnseenByManager(@PathVariable("mgr_id") Long id){
			return new ResponseEntity<ArrayList<RestaurantRegistry>>((ArrayList<RestaurantRegistry>) service.getRegisteredRestaurantsByManagerId(id),HttpStatus.OK);
		}
		
		/**
		 * GET
		 * Admin hasn't updated.
		 * */
		@CrossOrigin(origins = "http://localhost:4200")
		@RequestMapping(value = path_prefix +"getPendingForAdmin",
						method=RequestMethod.GET,
						produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<ArrayList<RestaurantRegistry>> getPendingForAdmin(){
			return new ResponseEntity<ArrayList<RestaurantRegistry>>((ArrayList<RestaurantRegistry>)service.getNotDecidedRestaurants(),HttpStatus.OK);
		}
		
		/**
		 * PUT
		 * Admin Accepted
		 * */
		@CrossOrigin(origins = "http://localhost:4200")
		@RequestMapping(value = path_prefix +"adminAccepted/{rest_id}",
						method=RequestMethod.PUT,
						produces=MediaType.APPLICATION_JSON_VALUE
						)
		@ResponseBody
		public ResponseEntity<RestaurantRegistry> updateRegistry_ACCEPTED(@PathVariable("rest_id") Long rest_id){
			RestaurantRegistry reg = service.findOne(rest_id);
			EmployeeBean emp = employee_service.getMGR_fromRestaurantREGISTRY(rest_id);
			
			reg.setStatus(RestaurantRegistry.RegistryStatus.ACCEPTED);
			
			service.update(reg);
			
			RestaurantBean restaurant = new RestaurantBean();
			restaurant.setName(reg.getRestaurant_name());
			restaurant.setType(reg.getType());
			Set<EmployeeBean> managers = new HashSet<EmployeeBean>();
			managers.add(emp);
			restaurant.setManagers(managers);
			restaurant = restaurant_service.create(restaurant);
			restaurant.setImage("/assets/pictures/restaurant_pictures/"+restaurant.getId()+".jpg");
			restaurant_service.update(restaurant);
			
			return new ResponseEntity<RestaurantRegistry>(reg,HttpStatus.OK);
		}
		
		/**
		 * PUT
		 * Admin declined
		 * */
		@CrossOrigin(origins = "http://localhost:4200")
		@RequestMapping(value = path_prefix + "adminDeclined/{rest_id}",
						method=RequestMethod.PUT,
						produces=MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public ResponseEntity<RestaurantRegistry> updateRegistry_DECLINED(@PathVariable("rest_id") Long rest_id){
			RestaurantRegistry reg = service.findOne(rest_id);
			
			reg.setStatus(RestaurantRegistry.RegistryStatus.DECLINED);
			
			service.update(reg);
			
			return new ResponseEntity<RestaurantRegistry>(reg,HttpStatus.OK);
		}
		
		/**
		 * PUT
		 * Manager saw restaurant status
		 * */
		@CrossOrigin(origins = "http://localhost:4200")
		@RequestMapping(value = path_prefix + "managerSawStatus/{rest_id}",
						method=RequestMethod.PUT,
						produces=MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public ResponseEntity<RestaurantRegistry> updateRegistry_SAWUPDATE(@PathVariable("rest_id") Long rest_id){
			RestaurantRegistry reg = service.findOne(rest_id);
			reg.setSeen(1);
			service.update(reg);
			
			return new ResponseEntity<RestaurantRegistry>(reg,HttpStatus.OK);
		}
		
		/**
		 * POST
		 * create new restaurant registry
		 * payload is the registry
		 * path contains manager id
		 * */
		@CrossOrigin(origins="http://localhost:4200")
		@RequestMapping(value = path_prefix + "newRegistry/{mgr_id}",
						method=RequestMethod.POST,
						produces=MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public ResponseEntity<RestaurantRegistry> newRegistry(@PathVariable("mgr_id") Long mgr_id, 
															  @RequestBody RestaurantRegistry registry){
			
			RestaurantRegistry reg = service.create(registry);
			EmployeeBean emp = employee_service.findOne(mgr_id);
			Set<EmployeeBean> emps = new HashSet<EmployeeBean>();
			emps.add(emp);
			reg.setRegistered_by(emps);
			service.update(reg);
			
			return new ResponseEntity<RestaurantRegistry>(reg,HttpStatus.OK);
		}
		
		/**
		 * GET ALL REGS
		 * */
		@CrossOrigin(origins = "http://localhost:4200")
		@RequestMapping(value=path_prefix+"getAllRegistries",
						method=RequestMethod.GET,
						produces=MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public ResponseEntity<ArrayList<RestaurantRegistry>> getAllRegs(){
			return new ResponseEntity<ArrayList<RestaurantRegistry>>((ArrayList<RestaurantRegistry>)service.findAll(),HttpStatus.OK);
		}
}
