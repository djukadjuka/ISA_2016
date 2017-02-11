package com.example.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.domain.RestaurantBean;
import com.example.domain.TableBean;
import com.example.service.TableService;
import com.example.service.TableServiceBean;

public class TableController {

	@Autowired
	private TableService tableService = new TableServiceBean();
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value="/getAllTables",
			method = RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<ArrayList<TableBean>> getAllTables(){
		ArrayList<TableBean> allTables = (ArrayList<TableBean>) tableService.findAll();
		
		return new ResponseEntity<ArrayList<TableBean>>(allTables,HttpStatus.OK);
	}
	
	
}
