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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.EmployeeBean;
import com.example.domain.UserBean;
import com.example.service.BillService;
import com.example.service.BillServiceBean;
import com.example.service.EmployeeService;
import com.example.service.EmployeeServiceBean;
import com.example.service.UserService;
import com.example.service.UserServiceBean;

@RestController
public class EmployeeController {

	@Autowired
	private BillService billService = new BillServiceBean();
	
	@Autowired 
	private EmployeeService employeeService = new EmployeeServiceBean();
	
	@Autowired
	private UserService userService = new UserServiceBean();
	
	/**FIRING EMPLOYEE*/
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value="/EmployeeController/fireEmployee/{user_id}",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public synchronized void fireEmployee(@PathVariable("user_id") Long user_id){
		this.employeeService.delete_his_schedules(user_id);
		
		this.employeeService.delete_his_tables(user_id);
		
		this.employeeService.delete_his_reviews(user_id);
		
		this.billService.delete_employee_bills(user_id);
		
		this.employeeService.fire_an_employee(user_id);
	}
	
	/**REGISTER NEW EMPLOYEE*/
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value="/EmployeeController/registerNewEmployee/{rest_id}",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public synchronized void registerNewEmployee(@RequestBody EmployeeBean employee,@PathVariable("rest_id") Long rest_id){
		String str = "";
		switch(employee.getRole()){
		case BARTENDER:	str = "BARTENDER";break;
		case COOK: str = "COOK";break;
		case MANAGER : str = "MANAGER";break;
		case WAITER : str = "WAITER";break;
		}
		System.out.println(employee.getDateOfBirth());
		
		this.employeeService.create_new_worker_for_restaurant(employee.getDateOfBirth(), 
									str, 
									employee.getShoeSize(), 
									employee.getSuitSize(), 
									employee.getId(), 
									rest_id);
		this.employeeService.delete_deliverer_that_became_manager(employee.getId());
	}
	
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
			value = "/EmployeeControler/getEmployeeById/{worker_id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Collection<EmployeeBean>> getEmployeesById(@PathVariable("worker_id") Long worker_id){
		System.out.println("TEST:"+worker_id);
		return new ResponseEntity<Collection<EmployeeBean>>(employeeService.getEmployeeById(worker_id),HttpStatus.OK);
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
