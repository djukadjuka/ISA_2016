package com.example.controller;

import java.util.ArrayList;
import java.util.Collection;
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

import com.example.domain.UserBean;
import com.example.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
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
	
//	@CrossOrigin(origins = "http://localhost:4200")
//	@RequestMapping(
//			value = "/getUsersFriends/{id}",
//			method = RequestMethod.GET,
//			produces = MediaType.APPLICATION_JSON_VALUE
//			)
//	public ResponseEntity<Set<UserBean>> getUsersFriends(@PathVariable("id") Long id){
//		UserBean userBean = userService.findOne(id);
//		if(userBean == null){
//			return new ResponseEntity<Set<UserBean>>(HttpStatus.NOT_FOUND);
//		}
//		return new ResponseEntity<Set<UserBean>>(userBean.getUsersFriends(),HttpStatus.OK);
//	}
	
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
}
