package com.example.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="restaurant_registry")
public class RestaurantRegistry {
	
	public enum RegistryStatus{
		PENDING,
		ACCEPTED,
		DECLINED
	}
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false,name = "restaurant_name")
	private String restaurant_name;
	
	@Column(nullable = false,name = "seen")
	private int seen;
	
	@Column(nullable = false,name = "type")
	private String type;
	
	@Column(nullable = false,name = "status")
	@Enumerated(EnumType.STRING)
	private RegistryStatus status;
	
	@Column(nullable = false,name = "deleted")
	private int deleted;

	@ManyToOne
	@JoinColumn(name = "registering_by", nullable = true)
	private EmployeeBean registering_by;
	
	@Override
	public int hashCode(){
		return (int) (13*((1 + this.getId()) * 13));
	}
	
	@Override
	public boolean equals(Object o){
		if(!(o instanceof RestaurantRegistry)){
			return false;
		}
		if(((RestaurantRegistry)o).getId() == this.getId()){
			return true;			
		}
		return false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRestaurant_name() {
		return restaurant_name;
	}

	public void setRestaurant_name(String restaurant_name) {
		this.restaurant_name = restaurant_name;
	}

	public int getSeen() {
		return seen;
	}

	public void setSeen(int seen) {
		this.seen = seen;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public RegistryStatus getStatus() {
		return status;
	}

	public void setStatus(RegistryStatus status) {
		this.status = status;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	@JsonIgnore
	public EmployeeBean getRegistering_by() {
		return registering_by;
	}

	public void setRegistering_by(EmployeeBean registering_by) {
		this.registering_by = registering_by;
	}
	
	
	
	
}
