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

	@Column(nullable = false)
	private int deleted;
	
	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int delete) {
		this.deleted = delete;
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
	
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof RestaurantZonesRelation))
			return false;
		if(this.getId() == ((RestaurantZonesRelation)obj).getId())
			return true;
		return false;
	}
	
	@Override
	public int hashCode(){
		return (int) (13*(1+13*this.getId()));
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

