package com.example.controller;

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

import com.example.domain.FriendshipBean;
import com.example.domain.FriendshipBean.FriendshipStatus;
import com.example.domain.UserBean;
import com.example.service.FriendshipService;
import com.example.service.FriendshipServiceBean;
import com.example.service.UserServiceBean;

@RestController
public class FriendshipController {

	@Autowired
	private FriendshipService friendshipService = new FriendshipServiceBean();
	@Autowired
	private UserServiceBean userService = new UserServiceBean();
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value = "/sendFriendRequest",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE
			)
	@ResponseBody
	public boolean sendFriendRequest(@RequestBody HashMap<String,UserBean> bp){
		
		FriendshipBean f = new FriendshipBean();
		
		f.setOriginator(bp.get("originator"));
		f.setRecipient(bp.get("recipient"));
		f.setStatus(FriendshipStatus.PENDING);
		
		friendshipService.create(f);
		
		return true;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value = "/getFriendships/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Collection<FriendshipBean>> getFriendships(@PathVariable("id") Long id){
		Collection<FriendshipBean> friendships = friendshipService.findByRecipient_idOrOriginator_idAndStatusAccepted(id);
		
		if(friendships == null){
			return new ResponseEntity<Collection<FriendshipBean>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Collection<FriendshipBean>>(friendships,HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value = "/getFriendRequests/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Collection<FriendshipBean>> getFriendRequests(@PathVariable("id") Long id){
		Collection<FriendshipBean> friendships = friendshipService.findByRecipient_idAndStatusPending(id);
	
		if(friendships == null){
			return new ResponseEntity<Collection<FriendshipBean>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Collection<FriendshipBean>>(friendships,HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value = "/respondFriendRequest",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE
			)
	@ResponseBody
	public boolean respondFriendRequest(@RequestBody FriendshipBean fs){
		
		if(fs == null)
			return false;
		
		friendshipService.update(fs);
		return true;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value = "/removeFromFriends/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public boolean removeFromFriends(@PathVariable("id") Long id){
		
		friendshipService.delete(id);
		FriendshipBean fs = friendshipService.findOne(id);
		
		if(fs == null)
			return true;
		else
			return false;
	}
}
