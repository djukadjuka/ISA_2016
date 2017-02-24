package com.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "restaurant_zone")
public class RestaurantZoneBean {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private int number_of_tables;
	
	@Column(nullable = false)
	private int tables_for_x;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private RestaurantBean restaurant;
	
	@Column(nullable = false)
	private int deleted;

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	@Override
	public int hashCode(){
		return (int) (13*(1+this.getId()*13));
	}
	
	@Override
	public boolean equals(Object o){
		if(!(o instanceof RestaurantZoneBean))
			return false;
		if(this.id == ((RestaurantZoneBean)o).getId())
			return true;
		return false;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber_of_tables() {
		return number_of_tables;
	}

	public void setNumber_of_tables(int number_of_tables) {
		this.number_of_tables = number_of_tables;
	}

	public int getTables_for_x() {
		return tables_for_x;
	}

	public void setTables_for_x(int tables_for_x) {
		this.tables_for_x = tables_for_x;
	}

	public RestaurantBean getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(RestaurantBean restaurant) {
		this.restaurant = restaurant;
	}
}
