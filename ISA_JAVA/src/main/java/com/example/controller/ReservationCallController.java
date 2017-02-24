package com.example.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.ReservationCallBean;
import com.example.service.ReservationCallServiceBean;

@RestController
public class ReservationCallController {
	
	@Autowired
	public ReservationCallServiceBean reservationCallService = new ReservationCallServiceBean();
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value="/getReservationsForOriginator/{id}",
			method = RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public Collection<ReservationCallBean> getReservationsForOriginator(@PathVariable("id") Long id)
	{
		return reservationCallService.findByOriginatorOriginator(id);
	}
}
