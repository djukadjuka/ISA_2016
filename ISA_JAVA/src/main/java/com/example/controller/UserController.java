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
import com.example.domain.RestaurantBean;
import com.example.domain.UserBean;
import com.example.domain.deliveryBeans.DelivererBean;
import com.example.service.EmployeeService;
import com.example.service.EmployeeServiceBean;
import com.example.service.FriendshipService;
import com.example.service.UserService;
import com.example.service.deliveryServices.DelivererService;
import com.example.service.deliveryServices.DelivererServiceBean;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	FriendshipService friendshipService;
	
	@Autowired EmployeeService employeeService = new EmployeeServiceBean();
	
	@Autowired
	DelivererService deliverer_service = new DelivererServiceBean();
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value = "/erect/a/monument/to/me/{user_id}",
			method = RequestMethod.GET
			)
	public synchronized String getUserString(@PathVariable("user_id") Long user_id){
		System.out.println("https://www.youtube.com/watch?v=UNZvuNCHq0M");

		String return_flag = "USER";
		
		UserBean user = this.userService.findOne(user_id);
		
		//check if employee
		EmployeeBean empl;
		if((empl = this.employeeService.findOne(user_id)) != null){
			
			//what kind of employee;
			return empl.getRole();
		}
		
		DelivererBean del;
		if((del = deliverer_service.findOne(user_id)) != null){
			
			if(del.getRequest_status().equals("ACCEPTED")){
				if(del.getFirst_login() == null){
					return "DELIVERER 0";
				}else{
					return "DELIVERER 1";
				}
			}
		}
		
		return return_flag;
	}
	
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
	
	////////////////////////////////////////////////////////
	/////////// AUTH SERVICES
	////////////////////////////////////////////////////////
	
	//da li postoji korisnik sa auth id
		//ako NE postoji ->
			//dodajes novog korisnika (BASIC)
			//postavis user id
			//rola
			// password na 0
	
		//ako postoji->
			//nadjes vratis :
				//user ID
				//rola
				//password na 1
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/auth/addNewUser",
					method = RequestMethod.POST,
					produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public synchronized AUTHReturnUserWrapper AUTHAddNewUser(@RequestBody AUTHUserWrapper auth_user){
		
		UserBean user = this.userService.findUserByAuthCodeYo(auth_user.getAuth_id());
		AUTHReturnUserWrapper wrapper = new AUTHReturnUserWrapper();
		
		if(user == null){
			user = new UserBean();
			
			user.setFirstName(auth_user.getFirst_name());
			user.setLastName(auth_user.getLast_name());
			user.setEmail(auth_user.getEmail());
			user.setProfilePicture(auth_user.getPicture());
			user.setAuth_code(auth_user.getAuth_id());
			user.setUsername(auth_user.getUsername());
			
			user = this.userService.create(user);
			
			wrapper.setPassword(null);
			wrapper.setUser_id(""+user.getId());
			wrapper.setUser_role("USER");
			
		}else{
			
			EmployeeBean radnik = this.employeeService.findOne(user.getId());
			wrapper.setUser_id(""+user.getId());

			wrapper.setPassword(user.getPassword()==null?0:1L);	// -.-
			wrapper.setManages_restaurants(new ArrayList<Long>());

			
			if(radnik == null){
				DelivererBean del = this.deliverer_service.findOne(user.getId());
				
				if(del == null || del.getRequest_status().equals("PENDING")){
					wrapper.setUser_role("USER");
				}else{
					wrapper.setUser_role("DELIVERER");
				}
				
			}else{
				wrapper.setUser_role(radnik.getRole());
				if(radnik.getRole().equals("MANAGER")){
					for(RestaurantBean rest : radnik.getManages()){
						wrapper.getManages_restaurants().add(rest.getId());
					}
				}				
			}
			
		}
		
		return wrapper;
		
	}
	
}

//*************** auth user wrapper
class AUTHReturnUserWrapper{
	private String user_role;
	private String user_id;
	private Long password;
	private ArrayList<Long> manages_restaurants;
	
	public ArrayList<Long> getManages_restaurants() {
		return manages_restaurants;
	}
	public void setManages_restaurants(ArrayList<Long> manages_restaurants) {
		this.manages_restaurants = manages_restaurants;
	}
	public String getUser_role() {
		return user_role;
	}
	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public Long getPassword() {
		return password;
	}
	public void setPassword(Long password) {
		this.password = password;
	}
	
}

class AUTHUserWrapper{
	private String first_name;
	private String last_name;
	private String email;
	private String picture;
	private String auth_id;
	private String username;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getAuth_id() {
		return auth_id;
	}
	public void setAuth_id(String auth_id) {
		this.auth_id = auth_id;
	}
	
}
