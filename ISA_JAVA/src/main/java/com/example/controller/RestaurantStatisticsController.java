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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.BillBean;
import com.example.domain.ratings.ReviewBean;
import com.example.service.BillService;
import com.example.service.BillServiceBean;
import com.example.service.ReviewService;
import com.example.service.ReviewServiceBean;

@RestController
public class RestaurantStatisticsController {
	
	@Autowired
	private ReviewService review_service = new ReviewServiceBean();
	
	@Autowired
	private BillService bill_service = new BillServiceBean();
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value="/restaurant_statistics/getAllRestaurantGrades/{rest_id}",
			method = RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	@ResponseBody
	public ResponseEntity<Collection<ReviewBean>> getAllRestaurantGrades(@PathVariable("rest_id") Long rest_id){
		return new ResponseEntity<Collection<ReviewBean>>(this.review_service.getAllRestaurantGrades(rest_id),HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value="/restaurant_statistics/gradesForProduct/{product_id}/inRestaurant/{rest_id}",
			method = RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Collection<ReviewBean>> getGradesForAProductRestaurant(@PathVariable("product_id") Long product_id,
																				 @PathVariable("rest_id") Long rest_id){
		return new ResponseEntity<Collection<ReviewBean>>(this.review_service.getAllGradesForAProductInARestaurant(rest_id, product_id),HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value="/restaurant_statistics/getGradesForEmployee/{emp_id}",
			method = RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Collection<ReviewBean>> getEmployeeGrades(@PathVariable("emp_id") Long emp_id){
		return new ResponseEntity<Collection<ReviewBean>>(this.review_service.getAllGradesForAnEmployee(emp_id),HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value="/restaurant_statistics/getAllRestaurantBills/{rest_id}",
			method = RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Collection<BillBean>> getAllRestaurantBills(@PathVariable("rest_id") Long rest_id){
		return new ResponseEntity<Collection<BillBean>>(this.bill_service.getAllBillsForARestaurant(rest_id),HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value="/restaurant_statistics/getBillsForAnEmployee/{emp_id}",
			method = RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Collection<BillBean>> getBillsForAnEmployee(@PathVariable("emp_id") Long emp_id){
		return new ResponseEntity<Collection<BillBean>>(this.bill_service.getAllBillsForAnEmployee(emp_id),HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value="/restaurant_statistics/getBills/{rest_id}/from/{date_from}/to/{date_to}",
			method = RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Collection<BillBean>> getRestTimePeriodBills(@PathVariable("rest_id") Long rest_id,
																	   @PathVariable("date_from") Long date_from,
																	   @PathVariable("date_to") Long date_to){
		return new ResponseEntity<Collection<BillBean>>(this.bill_service.getAllBillsRestaurantTimePeriod(rest_id, date_from, date_to),HttpStatus.OK);
	}

}
