package com.example.service;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.domain.BillBean;

public interface BillService {

	BillBean findOne(Long id);
	
	Collection<BillBean> findAll();
	
	BillBean update(BillBean bill);
	
	BillBean create(BillBean bill);
	
	void delete(Long id);
	
	//custom
	/**Get all bills for a restaurant*/
	public Collection<BillBean> getAllBillsForARestaurant(Long rest_id);
	
	/**Get all bills for an employee*/
	public Collection<BillBean> getAllBillsForAnEmployee(Long emp_id);
	
	/**get all bills for a restaurant in a time period*/
	public Collection<BillBean> getAllBillsRestaurantTimePeriod(Long rest_id,Long date_from,Long date_to);
	
	public void delete_employee_bills(Long emp_id);
}
