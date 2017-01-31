package com.example.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="Product")
public class ProductBean {

	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false)
	private float price;
	
	@Column(nullable = false)
	private String description;
	
	@Column(nullable = false)
	private String name;
	
	@ManyToMany(mappedBy = "foodMenu")
	private Set<RestaurantBean> restaurantsFood;
	
	@ManyToMany(mappedBy = "drinksMenu")
	private Set<RestaurantBean> restaurantsDrinks;
	
	@Override
	public boolean equals(Object obj) {
		ProductBean prod = (ProductBean)obj;
		if(this.id == prod.getId())
			return true;
		else
			return false;
	};
	
	@Override
	public int hashCode() {
		return (int) this.id;
	};
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
