package com.example.controller;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.ReservationBean;
import com.example.domain.TableBean;
import com.example.domain.TableBean.TableStatus;
import com.example.service.ReservationServiceBean;
import com.example.service.TableService;
import com.example.service.TableServiceBean;


@RestController
public class TableController {

	@Autowired
	private TableService tableService = new TableServiceBean();
	@Autowired
	private ReservationServiceBean reservationService = new ReservationServiceBean();
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value="/getAllTables/{zone_id}",
			method = RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Collection<TableBean>> getAllTables(@PathVariable("zone_id") Long zone_id){
		Collection<TableBean> tables = tableService.findAllTablesByZoneId(zone_id);
		
		//prvo proveriti da li su stolovi zauzeti, promeniti im status shodno tome
		
		Date date = new Date();
		Long time = date.getTime();
		
		for(TableBean t : tables)
		{
			Collection<ReservationBean> reservations = reservationService.findReservationsByTableId(t.getId());
			
			for(ReservationBean r : reservations)
			{
				if(time > r.getStartDate() && time < r.getEndDate())
				{
					t.setStatus(TableStatus.TAKEN);
					//tableService.updateTableStatus(t);
					break;
				}
			}
		}
		
		if(tables == null){
			return new ResponseEntity<Collection<TableBean>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Collection<TableBean>>(tables,HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value="/tableController/getTablesForRestaurant/{rest_id}",
			method = RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	@ResponseBody
	public ResponseEntity<Collection<TableBean>> getTablesForRestaurant(@PathVariable("rest_id") Long rest_id){
		return new ResponseEntity<Collection<TableBean>>(tableService.findAllTablesFromRestaurant(rest_id),HttpStatus.OK);
	}
	
}
