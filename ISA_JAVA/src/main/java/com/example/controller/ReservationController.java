package com.example.controller;

import java.util.Collection;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.ReservationBean;
import com.example.service.ReservationServiceBean;
import com.example.service.TableServiceBean;

@RestController
public class ReservationController {
	
	@Autowired
	private ReservationServiceBean service = new ReservationServiceBean();
	@Autowired
	private TableServiceBean tableService = new TableServiceBean();
	
		synchronized
		@CrossOrigin(origins = "http://localhost:4200")
		@RequestMapping(
				value = "/makeReservation",
				method = RequestMethod.POST,
				produces = MediaType.APPLICATION_JSON_VALUE,
				consumes = MediaType.APPLICATION_JSON_VALUE
				)
		@ResponseBody
		public boolean makeReservation(@RequestBody HashMap<String,String> reservation){
			
			Long startDate = Long.decode(reservation.get("startDate"));
			Long endDate = Long.decode(reservation.get("endDate"));
			Long table_id = Long.decode(reservation.get("table_id"));
			
			Collection<ReservationBean> reservations = null;
			
			if(startDate != null && endDate != null)
				reservations = service.findReservationsMatch(startDate, endDate, table_id);
			
			//ako smo pronasli poklapanje sa query-em, znaci da postoje rezervacije u tom terminu vec!
			if(!reservations.isEmpty())
				return false;
			
			ReservationBean r = new ReservationBean(tableService.findOne(table_id));
			r.setEndDate(endDate);
			r.setStartDate(startDate);
			service.create(r);
			
			return true;
		}
		
		
}
