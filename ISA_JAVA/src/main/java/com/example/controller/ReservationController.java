package com.example.controller;

import java.util.Collection;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.ReservationBean;
import com.example.domain.ReservationCallBean;
import com.example.domain.UserBean;
import com.example.domain.ReservationCallBean.ReservationStatus;
import com.example.service.ReservationCallServiceBean;
import com.example.service.ReservationServiceBean;
import com.example.service.TableServiceBean;
import com.example.service.UserServiceBean;

@RestController
public class ReservationController {
	
	@Autowired
	private ReservationServiceBean reservationService = new ReservationServiceBean();
	@Autowired
	private TableServiceBean tableService = new TableServiceBean();
	@Autowired
	private ReservationCallServiceBean reservationCallService = new ReservationCallServiceBean();
	@Autowired
	private UserServiceBean userService = new UserServiceBean();
	
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
			Long originator = Long.decode(reservation.get("originator"));
			
			Collection<ReservationBean> reservations = null;
			
			if(startDate != null && endDate != null)
				reservations = reservationService.findReservationsMatch(startDate, endDate, table_id);
			
			//ako smo pronasli poklapanje sa query-em, znaci da postoje rezervacije u tom terminu vec!
			if(!reservations.isEmpty())
				return false;
			
			ReservationBean r = new ReservationBean(tableService.findOne(table_id));
			r.setEndDate(endDate);
			r.setStartDate(startDate);
			reservationService.create(r);
			
			UserBean u = userService.findOne(originator);
			
			ReservationCallBean rcs = new ReservationCallBean(ReservationStatus.ACCEPTED,u,u,r);
			reservationCallService.create(rcs);
			
			
			return true;
		}
}
