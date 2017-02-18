package com.example.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.TableBean;
import com.example.service.TableService;
import com.example.service.TableServiceBean;


@RestController
public class TableController {

	@Autowired
	private TableService tableService = new TableServiceBean();
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value="/getAllTables/{zone_id}",
			method = RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Collection<TableBean>> getAllTables(@PathVariable("zone_id") Long zone_id){
		Collection<TableBean> tables = tableService.findAllTablesByZoneId(zone_id);
		
		if(tables == null){
			return new ResponseEntity<Collection<TableBean>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Collection<TableBean>>(tables,HttpStatus.OK);
	}
	
	
}
