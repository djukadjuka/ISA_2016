package com.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="restaurant_has_zones")
public class RestaurantZonesRelation {
	
	@Id
	@GeneratedValue
	private long id;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name="rest_id")
	private RestaurantBean restaurant;
	
	@ManyToOne
	@JoinColumn(name="zone_id")
	private RestaurantZone zone_id;
	
	@Column(name="table_for_x",nullable=false)
	private long tableForX;
	
	@Column(name="amoun_of_tables",nullable=false)
	private int amountOfTables;

	@JsonIgnore
	public RestaurantBean getRestaurant() {
		return restaurant;
	}
	
	@JsonIgnore
	public void setRestaurant(RestaurantBean restaurant) {
		this.restaurant = restaurant;
	}
	
	public RestaurantZone getZone_id() {
		return zone_id;
	}
	
	public void setZone_id(RestaurantZone zone_id) {
		this.zone_id = zone_id;
	}

	public long getTableForX() {
		return tableForX;
	}

	public void setTableForX(long tableForX) {
		this.tableForX = tableForX;
	}

	public int getAmountOfTables() {
		return amountOfTables;
	}

	public void setAmountOfTables(int amountOfTables) {
		this.amountOfTables = amountOfTables;
	}
}

