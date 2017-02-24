package com.example.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.EmployeeBean;
import com.example.domain.EmployeeScheduleBean;
import com.example.service.EmployeeScheduleService;
import com.example.service.EmployeeScheduleServiceBean;
import com.example.service.EmployeeService;
import com.example.service.EmployeeServiceBean;
import com.example.service.UserService;
import com.example.service.UserServiceBean;

@RestController
public class ScheduleController {

	@Autowired
	private EmployeeScheduleService schedule_service= new EmployeeScheduleServiceBean();
	
	@Autowired
	private EmployeeService employee_service = new EmployeeServiceBean();
	
	@Autowired
	private UserService user_service = new UserServiceBean();
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value="schedz/getForEmployee/{employee_id}",
					method = RequestMethod.GET,
					produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ArrayList<EmployeeScheduleBean>> getScheduleForEmployee(@PathVariable("employee_id") Long employee_id){
		return new ResponseEntity<ArrayList<EmployeeScheduleBean>>(
				(ArrayList<EmployeeScheduleBean>) schedule_service.getScheddzzzForEmployee(employee_id),HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value="schedz/getEmployeesForRestaurant/{rest_id}",
					method= RequestMethod.GET,
					produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ArrayList<EmployeeBean>> getEmployeesForRestaurant(@PathVariable("rest_id") Long rest_id){
		return new ResponseEntity<ArrayList<EmployeeBean>>(
				(ArrayList<EmployeeBean>)employee_service.getWorkersThatWorkForARestaurant(rest_id),HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value="schedz/getScheduleByDate",
					method= RequestMethod.GET,
					produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public  ResponseEntity<ArrayList<EmployeeScheduleBean>> getScheduleByDate(){
		
		return new ResponseEntity<ArrayList<EmployeeScheduleBean>>(
				(ArrayList<EmployeeScheduleBean>)schedule_service.getSchedduleForEmployee(), HttpStatus.OK);
	}
	
}
