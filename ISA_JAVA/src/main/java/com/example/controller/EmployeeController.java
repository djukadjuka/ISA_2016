package com.example.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.EmployeeBean;
import com.example.domain.UserBean;
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
	
	/**REMOVE MANAGER FROM RESTAURANT*/
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value = "/EmployeeControler/sendRemovingManagerPack/{rest_id}",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE
			)
	public synchronized void fireSomeManagers(@RequestBody ArrayList<UserBean> user_payload,@PathVariable("rest_id") Long rest_id){
		
		for(UserBean user : user_payload){
			this.userService.destroyManagerRestaurantTies(user.getId(), rest_id);
			
			if(this.userService.getTiesToRestaurantByThisManager(user.getId()).isEmpty()){
				this.userService.fireManagerAllTogether(user.getId());
			}
			
		}
	}
	
	
	/**UPDATE MANAGERS FOR RESTAURANT*/
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value = "/EmployeeControler/sendNewManagerPack/{rest_id}",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE
			)
	public synchronized void registerNewManagers(@RequestBody ArrayList<UserBean> user_payload,@PathVariable("rest_id") Long rest_id){
		
		ArrayList<UserBean> got_users = new ArrayList<>();
		
		for(UserBean u : user_payload){
			got_users.add(this.userService.findOne(u.getId()));
		}
		
		for(UserBean u : got_users){
			//delete users that want to be a manager but were waiting on being a deliverer
			this.employeeService.delete_deliverer_that_became_manager(u.getId());
			
			System.out.println(this.employeeService.check_if_user_already_manager(u.getId()));
			
			if(this.employeeService.check_if_user_already_manager(u.getId()) == null){
				this.employeeService.insert_new_manager(u.getId());
			}
			
			this.employeeService.create_new_manager_relation(u.getId(), rest_id);
			
		}
		
	}
	
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
