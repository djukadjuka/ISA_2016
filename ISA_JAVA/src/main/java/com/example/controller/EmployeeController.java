package com.example.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.EmployeeBean;
import com.example.service.EmployeeService;
import com.example.service.EmployeeServiceBean;
import com.example.service.UserService;
import com.example.service.UserServiceBean;

@RestController
public class EmployeeController {

	@Autowired 
	private EmployeeService employeeService = new EmployeeServiceBean();
	
	@Autowired
	private UserService userService = new UserServiceBean();
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value = "/EmployeeControler/getEmployeesForRestaurant/{rest_id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Collection<EmployeeBean>> getEmployeesForRestaurant(@PathVariable("rest_id") Long rest_id){
		
		return new ResponseEntity<Collection<EmployeeBean>>(employeeService.getWorkersThatWorkForARestaurant(rest_id),HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value = "/EmployeeControler/getWaitersForRestaurant/{rest_id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Collection<EmployeeBean>> getWaitersForARestaurant(@PathVariable("rest_id") Long rest_id){
		return new ResponseEntity<Collection<EmployeeBean>>(employeeService.getWaitersForARestaurant(rest_id),HttpStatus.OK);
	}
}
