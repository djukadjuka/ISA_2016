package com.example.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class RestaurantBean implements Serializable{

	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String type;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
	private Set<ReviewBean> reviews;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private Set<ProductBean> foodMenu;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private Set<ProductBean> drinksMenu;
	
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}