package com.example.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="restaurant_zone")
public class RestaurantZone {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "zone_id")
	private Set<RestaurantZonesRelation> relation;
	
	@Override
	public int hashCode(){
		int prime = 13;
		return (int) (prime+prime*(1+this.getId()));
	}
	
	@Override
	public boolean equals(Object zone){
		if(!(zone instanceof RestaurantZone))
			return false;
		if(this.getId() == ((RestaurantZone)zone).getId())
			return true;
		return false;
	}
	
	@JsonIgnore
	public Set<RestaurantZonesRelation> getRelation() {
		return relation;
	}

	@JsonIgnore
	public void setRelation(Set<RestaurantZonesRelation> relation) {
		this.relation = relation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
