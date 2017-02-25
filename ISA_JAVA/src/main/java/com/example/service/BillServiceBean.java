package com.example.service;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.BillBean;
import com.example.repository.BillRepository;

@Service
public class BillServiceBean implements BillService{

	@Autowired
	private BillRepository repository;
	
	@Override
	public BillBean findOne(Long id) {
		return this.repository.findOne(id);
	}

	@Override
	public Collection<BillBean> findAll() {
		return this.repository.findAll();
	}

	@Override
	public BillBean update(BillBean bill) {
		return this.repository.save(bill);
	}

	@Override
	public BillBean create(BillBean bill) {
		return this.repository.save(bill);
	}

	@Override
	public void delete(Long id) {
		this.repository.delete(id);
	}

	@Override
	public Collection<BillBean> getAllBillsForARestaurant(Long rest_id) {
		return this.repository.getAllBillsForARestaurant(rest_id);
	}

	@Override
	public Collection<BillBean> getAllBillsForAnEmployee(Long emp_id) {
		return this.repository.getAllBillsForAnEmployee(emp_id);
	}

	@Override
	public Collection<BillBean> getAllBillsRestaurantTimePeriod(Long rest_id, Long date_from, Long date_to) {
		return this.repository.getAllBillsRestaurantTimePeriod(rest_id, date_from, date_to);
	}

	@Override
	public void delete_employee_bills(Long emp_id) {
		this.repository.delete_employee_bills(emp_id);
	}

}
