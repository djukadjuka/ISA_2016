package com.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "Restaurant_table")
public class TableBean {
	
	public enum TableStatus {
        FREE,
        TAKEN
    }

	@Id
	@GeneratedValue
	@Column(name = "table_id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "max_people", unique = false, nullable = false)
	private Integer maxPeople;

	@Column(name = "image",  nullable = true)
	private String image;

	@Column(name = "status", unique = false, nullable = false)
	@Enumerated(EnumType.STRING)
	private TableStatus status;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_zone_id", nullable = false)
    private RestaurantZoneBean restaurant_zone_id;
	
	/*@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "served_by", nullable = true)
	private EmployeeBean served_by;*/
	
	/*public EmployeeBean getServed_by() {
		return served_by;
	}

	public void setServed_by(EmployeeBean served_by) {
		this.served_by = served_by;
	}*/

	public TableBean() {
		super();
		
	}

	public TableBean(RestaurantZoneBean rzb) {
        this.status = TableStatus.FREE;
        this.restaurant_zone_id = rzb;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Integer getMaxPeople() {
		return maxPeople;
	}

	public void setMaxPeople(Integer maxPeople) {
		this.maxPeople = maxPeople;
	}

	public String getImage() {
		return image;
		}

	public TableStatus getStatus() {
		return status;
	}

	public void setStatus(TableStatus status) {
		this.status = status;
	}
	
	public RestaurantZoneBean getRestaurant_zone_id() {
		return restaurant_zone_id;
	}

	public void setRestaurant_zone_id(RestaurantZoneBean restaurant_zone_id) {
		this.restaurant_zone_id = restaurant_zone_id;
	}
}
