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
	
	/**
	 * 	Metoda vraca stolove koji pripadaju odredjenoj zoni restorana
	 *  i pritom vrsi proveru za dati pocetni i krajnji datum, da li su
	 *  stolovi slobodni i menja im status u free ili taken
	 */
	private ReservationServiceBean reservationService = new ReservationServiceBean();
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value="/getAllTables/{zone_id}/{startDate}/{endDate}",
			method = RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Collection<TableBean>> getAllTables(@PathVariable("zone_id") Long zone_id,
															  @PathVariable("startDate") Long startDate,
															  @PathVariable("endDate") Long endDate){
		
		Collection<TableBean> tables = tableService.findAllTablesByZoneId(zone_id);
		
		//Prvo sledi provera da li su stolovi zauzeti za dati termin 
		
		for(TableBean t : tables)
		{
			Collection<ReservationBean> reservations = reservationService.findReservationsByTableId(t.getId());
			boolean taken = false;
			
			for(ReservationBean r : reservations)
			{
				if((startDate <= r.getStartDate() && endDate > r.getStartDate())
				    || (startDate < r.getEndDate() && endDate > r.getEndDate())
				    || (startDate >= r.getStartDate() && endDate <= r.getEndDate()))
				{
					tableService.updateTableStatus(t.getId(), "TAKEN");
					t.setStatus(TableStatus.TAKEN);
					taken = true;
					break;
				}
			}
			
			if(!taken)
			{
				tableService.updateTableStatus(t.getId(), "FREE");
				t.setStatus(TableStatus.FREE);
			}
		}
		
		if(tables == null){
			return new ResponseEntity<Collection<TableBean>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Collection<TableBean>>(tables,HttpStatus.OK);
	}
	
	
}
