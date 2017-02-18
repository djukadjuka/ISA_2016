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

import com.example.domain.FriendshipBean.FriendshipStatus;


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
	private Integer id;
	
	@Column(name = "max_people", unique = false, nullable = false)
	private Integer maxPeople;

	@Column(name = "status", unique = false, nullable = false)
	@Enumerated(EnumType.STRING)
	private TableStatus status;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_zone_id", nullable = false)
    private RestaurantZoneBean restaurant_zone_id;
	
	public TableBean() {
		super();
		
	}
	
	public TableBean(RestaurantZoneBean rzb) {
        this.status = TableStatus.FREE;
        this.restaurant_zone_id = rzb;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMaxPeople() {
		return max_people;
	}

	public void setMaxPeople(Integer maxPeople) {
		this.max_people = maxPeople;
	}

	public TableStatus getStatus() {
		return status;
	}

	public void setStatus(TableStatus status) {
		this.status = status;
	}
}
