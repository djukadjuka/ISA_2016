package com.example.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

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

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value="/restaurant_statistics/getAttendanceForYear/{year_number}/for_restaurant/{rest_id}",
			method = RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<HashMap<Integer,Integer>> getAttendanceForYear(@PathVariable("year_number") Long year_number, @PathVariable("rest_id") Long rest_id) throws ParseException{
		HashMap<Integer,Integer> payload = new HashMap<>();
		/*for(int i=1;	i<=52;	i++){
			payload.put(i, 0);
		}*/
		Long one_week_in_millis = (long) (1000*60*60*24*7);
		//Long one_day_in_millis = (long)(1000*60*60*24);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		String start_date_string = "01/01/"+year_number+" 00:00:00";
		String end_date_string = "7/01/"+year_number+" 23:59:59";
		
		Date start_date = dateFormat.parse(start_date_string);
		
		Date end_date = dateFormat.parse(end_date_string);
		
		for(int i=1;	i<=52;	i++){
			System.out.println(start_date);
			ArrayList<BillBean> bills =  (ArrayList<BillBean>) this.bill_service.getAllBillsRestaurantTimePeriod(rest_id, start_date.getTime(), end_date.getTime());
			System.out.println(end_date);
			payload.put(i, bills.size());
			start_date.setTime(start_date.getTime() + one_week_in_millis);
			end_date.setTime(end_date.getTime() + one_week_in_millis);
		}
		
		return new ResponseEntity<HashMap<Integer,Integer>>(payload,HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value="/restaurant_statistics/getAttendanceFromDay/{day_start}/toDay/{day_end}/for_restaurant/{rest_id}",
			method = RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<HashMap<Long,Integer>> getAttendanceForDays(@PathVariable("day_start") Long day_start,
																		 @PathVariable("day_end") Long day_end,
																		 @PathVariable("rest_id") Long rest_id) throws ParseException{
		HashMap<Long,Integer> payload = new HashMap<>();
		
		Long one_day_in_millis = (long)(1000*60*60*24);
		
		Date start_date = new Date(day_start);
		Date end_date = new Date(day_end);
		
		while(start_date.getTime() < end_date.getTime()){
			ArrayList<BillBean> bills =  (ArrayList<BillBean>) this.bill_service.getAllBillsRestaurantTimePeriod(rest_id, start_date.getTime(),start_date.getTime()+one_day_in_millis-1000);
			payload.put(start_date.getTime(),bills.size());
			start_date.setTime(start_date.getTime()+one_day_in_millis);
		}
		
		return new ResponseEntity<HashMap<Long,Integer>>(payload,HttpStatus.OK);
	}
}
