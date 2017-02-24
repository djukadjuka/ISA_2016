package com.example.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bill")
public class BillBean implements Serializable {

	private static final long serialVersionUID = -8354852449631783895L;

	@Id
	@GeneratedValue
	private Integer id;

	@OneToOne(cascade = CascadeType.ALL)
	private RestaurantBean restaurant;
	
	@OneToOne(cascade = CascadeType.ALL)
	private EmployeeBean employee;
	
	@Column(name = "cash", nullable=true)
	private Long cash;
	
	@Column(name = "date_of_transaction",nullable = true)
	private Long date_of_transaction;
	
	//ubacujte dalje sta vam treba pa menjajte data.sql
	
	public BillBean() {
		super();
	}

	public RestaurantBean getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(RestaurantBean restaurant) {
		this.restaurant = restaurant;
	}

	public EmployeeBean getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeBean employee) {
		this.employee = employee;
	}

	public Long getCash() {
		return cash;
	}

	public void setCash(Long cash) {
		this.cash = cash;
	}

	public Long getDate_of_transaction() {
		return date_of_transaction;
	}

	public void setDate_of_transaction(Long date_of_transaction) {
		this.date_of_transaction = date_of_transaction;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}

