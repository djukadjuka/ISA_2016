package com.example.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.ReservationCallBean;
import com.example.domain.ReservationCallBean.ReservationStatus;
import com.example.service.ReservationCallServiceBean;
import com.example.service.UserServiceBean;

@RestController
public class ReservationCallController {
	
	@Autowired
	public ReservationCallServiceBean reservationCallService = new ReservationCallServiceBean();
	
	@Autowired
	public UserServiceBean userService = new UserServiceBean();
	
	synchronized
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value="/getReservationsForOriginator/{id}",
			method = RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ArrayList<ReservationCallBean> getReservationsForOriginator(@PathVariable("id") Long id)
	{
		Date date = new Date();
		Long time = date.getTime() + 1800000; //30 min pre pocetka rez moze da otkaze
		
		Collection<ReservationCallBean> calls = reservationCallService.findByOriginatorOriginator(id);
		ArrayList<ReservationCallBean> retVal = new ArrayList<>();
		
		Iterator<ReservationCallBean> iterator = calls.iterator();
		
		while(iterator.hasNext())
		{
			ReservationCallBean rcb = iterator.next();
			if(time < rcb.getReservation().getStartDate())
				retVal.add(rcb);
		}
		
		return retVal;
	}
	
	synchronized
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value="/reservationInvite",
			method = RequestMethod.POST,
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE
			)
	public boolean reservationInvite(@RequestBody ReservationCallBean rcb)
	{	
		ReservationCallBean call = new ReservationCallBean(ReservationStatus.PENDING,rcb.getOriginator(), rcb.getRecipient(),rcb.getReservation());
		
		reservationCallService.create(call);
		
		return true;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value = "/cancelReservation/{res_id}/{res_call_id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public boolean cancelReservation(@PathVariable("res_id") Long res_id, @PathVariable("res_call_id") Long res_call_id){
		
		reservationCallService.delete(res_id, res_call_id);
		
		return true;
	}
}
