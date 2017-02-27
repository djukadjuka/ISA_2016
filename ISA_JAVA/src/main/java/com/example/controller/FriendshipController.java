package com.example.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

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

@RestController
public class FriendshipController {

	@Autowired
	private FriendshipService friendshipService = new FriendshipServiceBean();
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value = "/sendFriendRequest",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE
			)
	@ResponseBody
	public ResponseEntity<Boolean> sendFriendRequest(@RequestBody HashMap<String,UserBean> bp){
		
		UserBean orig = bp.get("originator");
		UserBean rec = bp.get("recipient");
		
		//prvo provera da li je mozda vec poslat zahtev
		FriendshipBean friendship = friendshipService.findByRecipientOrOriginatorCombination(orig.getId(), rec.getId());
		
		if(friendship != null)
			return new ResponseEntity<Boolean> (HttpStatus.NOT_FOUND);
		
		FriendshipBean f = new FriendshipBean();
		
		f.setOriginator(orig);
		f.setRecipient(rec);
		f.setStatus(FriendshipStatus.PENDING);
		
		friendshipService.create(f);
		
		return new ResponseEntity<Boolean> (true, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value = "/getFriendships/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<ArrayList<FriendshipBean>> getFriendships(@PathVariable("id") Long id){
		Collection<FriendshipBean> friendships = friendshipService.findByRecipient_idOrOriginator_idAndStatusAccepted(id);
		
		//Dobio sam sve friendshipe, ali cu namestiti da su prijatelji korisnika koji je zatrazio ovaj servis
		//postavljeni u friendship-u na recipient, kako bi tamo mogao da ih prikazem skladno tome
		ArrayList<FriendshipBean> retVal = new ArrayList<>();
		
		retVal.addAll(friendships); 
		
		for(FriendshipBean fb : retVal)
		{
			if(fb.getRecipient().getId().equals(id))
			{
				UserBean temp = fb.getOriginator();
				fb.setOriginator(fb.getRecipient());
				fb.setRecipient(temp);
			}
		}
		
		
		if(friendships == null){
			return new ResponseEntity<ArrayList<FriendshipBean>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ArrayList<FriendshipBean>>(retVal,HttpStatus.OK);
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
