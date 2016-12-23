package com.example.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class RestaurantBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String type;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
	private Set<ReviewBean> reviews;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(	name = "restaurant_bean_food_menu",
				catalog = "isa_database",
				joinColumns = {
						@JoinColumn(name = "rest_id", nullable = false, updatable = false)
				},
				inverseJoinColumns = {
						@JoinColumn(name = "food_id", nullable = false, updatable = false)
				}
			)
	private Set<ProductBean> foodMenu;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(	name = "restaurant_bean_drinks_menu",
				catalog = "isa_database",
				joinColumns = {
						@JoinColumn(name = "rest_id", nullable = false, updatable = false)
				},
				inverseJoinColumns = {
						@JoinColumn(name = "drink_id", nullable = false, updatable = false)
				}
			)
	private Set<ProductBean> drinksMenu;
	
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
