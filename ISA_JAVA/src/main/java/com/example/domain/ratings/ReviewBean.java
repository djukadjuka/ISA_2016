package com.example.domain.ratings;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.domain.EmployeeBean;
import com.example.domain.ProductBean;
import com.example.domain.ReservationCallBean;
import com.example.domain.RestaurantBean;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="review")
public class ReviewBean {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="grade")
	private Long grade;		//radite kao da moze od nekolko do nekolko .... ne treba enum misim realno ...
	
	@Column(name="short_description",nullable= true) //ako vam uopste treba....
	private String short_description;
	
	@JsonIgnore
	@ManyToOne													//ako je za restoran
	@JoinColumn(name = "for_restaurant", nullable = true)		//ostalo ce biti null
	private RestaurantBean for_restaurant;
	
	@JsonIgnore
	@ManyToOne													//ako je za employee
	@JoinColumn(name = "for_employee",nullable=true)			//onda ce ostalo biti null
	private EmployeeBean for_employee;
	
	@JsonIgnore
	@ManyToOne													//ako je za produkt
	@JoinColumn(name = "for_product", nullable=true)			//samo ce employee biti null
	private ProductBean for_product;							//ako trazis posebno ocenu za restoran	
																//mora ovo biti null

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGrade() {
		return grade;
	}

	public void setGrade(Long grade) {
		this.grade = grade;
	}

	public String getShort_description() {
		return short_description;
	}

	public void setShort_description(String short_description) {
		this.short_description = short_description;
	}

	public RestaurantBean getFor_restaurant() {
		return for_restaurant;
	}

	public void setFor_restaurant(RestaurantBean for_restaurant) {
		this.for_restaurant = for_restaurant;
	}

	public EmployeeBean getFor_employee() {
		return for_employee;
	}

	public void setFor_employee(EmployeeBean for_employee) {
		this.for_employee = for_employee;
	}

	public ProductBean getFor_product() {
		return for_product;
	}

	public void setFor_product(ProductBean for_product) {
		this.for_product = for_product;
	}

}