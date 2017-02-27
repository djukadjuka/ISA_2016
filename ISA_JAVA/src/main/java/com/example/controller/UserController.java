package com.example.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

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
import com.example.domain.FriendshipBean;
import com.example.domain.UserBean;
import com.example.service.EmployeeService;
import com.example.service.EmployeeServiceBean;
import com.example.service.FriendshipService;
import com.example.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	FriendshipService friendshipService;
	
	@Autowired EmployeeService employeeService = new EmployeeServiceBean();
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value = "/getUser/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<UserBean> getUser(@PathVariable("id") Long id){
		UserBean userBean = userService.findOne(id);
		if(userBean == null){
			return new ResponseEntity<UserBean>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UserBean>(userBean,HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value = "/checkUsername/{username}/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public boolean checkUsername(@PathVariable("username") String username, @PathVariable("id") Long id){
		Collection<UserBean> users = userService.findAll();
		
		for(UserBean user : users)
			if(user.getUsername().equals(username) && !user.getId().equals(id))
				return true;
		return false;
	}
	
	//vraca sve ljude kojima user jos nije poslao friend request
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value = "/getAllUsers/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Collection<UserBean>> getUsersFriends(@PathVariable("id") Long id){
		Collection<UserBean> users = userService.findAll();
		Collection<FriendshipBean> friendshipsOrig = friendshipService.findByOriginator_id(id);
		Collection<FriendshipBean> friendshipsRec = friendshipService.findByRecipient_id(id);
		
		if(users == null){
			return new ResponseEntity<Collection<UserBean>>(HttpStatus.NOT_FOUND);
		}
		
		//izbacim sam sebe
		for(UserBean user : users)
		{
			if(user.getId().equals(id))
			{
				users.remove(user);
				break;
			}
		}
		
		//izbacujem ako bilo gde vec postoji formiran poziv za prijateljstvo -> prihvacen, odbijen ili na cekanju
		for(FriendshipBean fs : friendshipsRec)
		{
			users.remove(fs.getOriginator());
		}
		

		for(FriendshipBean fs : friendshipsOrig)
		{
			users.remove(fs.getRecipient());
		}
		
		return new ResponseEntity<Collection<UserBean>>(users,HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value = "/updateUser",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE
			)
	@ResponseBody
	public ResponseEntity<UserBean> updateUser(@RequestBody UserBean user){
		UserBean userr = userService.findOne(user.getId());
		if(userr == null){
			return new ResponseEntity<UserBean>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		userService.update(user);
		return new ResponseEntity<UserBean>(user,HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
				value = "/userRepo/getManagers/{mgr_id}/forRestaurant/{rest_id}",
				method = RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_VALUE
			)
	@ResponseBody
	public ResponseEntity<HashMap<String,Object>> getDataForManagerEditing(@PathVariable("mgr_id") Long mgr_id,
																		   @PathVariable("rest_id") Long rest_id){
		HashMap<String,Object> ret = new HashMap<>();
		
		ArrayList<UserBean> freeUsers = (ArrayList<UserBean>) userService.getUsersNotManagingOrNotManagersForRestaurant(rest_id);
		ArrayList<UserBean> managers = (ArrayList<UserBean>) userService.getManagersForRestaurantNoCurrentManager(rest_id, mgr_id);
		
		ret.put("free_users", freeUsers);
		ret.put("managers", managers);
		
		return new ResponseEntity<HashMap<String,Object>>(ret,HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "userRepo/getEmployeeDataForRestaurant/{rest_id}",
					method = RequestMethod.GET,
					produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<HashMap<String,Object>> getDataForNewEmployees(@PathVariable("rest_id") Long rest_id){
		ArrayList<EmployeeBean> not_employed_workers = (ArrayList<EmployeeBean>) employeeService.getWorkersThatDoNotWorkForARestaurant();
		ArrayList<EmployeeBean> employed_workers = (ArrayList<EmployeeBean>) employeeService.getWorkersThatWorkForARestaurant(rest_id);
		ArrayList<UserBean> employed_users = (ArrayList<UserBean>) userService.getUsersThatWorkForARestaurant(rest_id);
		ArrayList<UserBean> not_employed_users = (ArrayList<UserBean>) userService.getusersThatDoNotWorkForARestaurant();
		
		HashMap<Long,EmployeeBean> not_employed_workers_map = new HashMap<>();
		HashMap<Long,EmployeeBean> employed_workers_map= new HashMap<>();
		HashMap<Long,UserBean> employed_users_map= new HashMap<>();
		HashMap<Long,UserBean> not_employed_users_map= new HashMap<>();
		
		for(EmployeeBean e : not_employed_workers){
			not_employed_workers_map.put(e.getId(), e);
		}
		for(EmployeeBean e : employed_workers){
			employed_workers_map.put(e.getId(), e);
		}
		for(UserBean u : employed_users){
			employed_users_map.put(u.getId(), u);
		}
		for(UserBean u : not_employed_users){
			not_employed_users_map.put(u.getId(), u);
		}
		
		HashMap<String,Object> ret = new HashMap<String,Object>();
		ret.put("not_employed_workers", not_employed_workers_map);
		ret.put("not_employed_users", not_employed_users_map);
		ret.put("employed_workers", employed_workers_map);
		ret.put("employed_users", employed_users_map);
		
		return new ResponseEntity<HashMap<String,Object>>(ret,HttpStatus.OK);
	}
	
	//get users that are not employees
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "userRepo/getPendingDeliverers",
					method = RequestMethod.GET,
					produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Collection<UserBean>> getIdleUsers(){
		return new ResponseEntity<Collection<UserBean>>(userService.getPossibleDeliverers(),HttpStatus.OK);
	}
}
