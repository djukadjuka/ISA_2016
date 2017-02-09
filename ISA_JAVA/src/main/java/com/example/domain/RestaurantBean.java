package com.example.domain;

import java.util.HashSet;
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
import javax.persistence.Table;

@Entity
@Table(name="Restaurant")
public class RestaurantBean{

	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String type;
	
	@Column(nullable = true)
	private String image;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
	private Set<ReviewBean> reviews = new HashSet<ReviewBean>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(	name = "restaurant_food_menu",
				catalog = "isa_database",
				joinColumns = 
						@JoinColumn(name = "rest_id", nullable = false, updatable = false)
				,
				inverseJoinColumns = 
						@JoinColumn(name = "food_id", nullable = false, updatable = false)
				
			)
	private Set<ProductBean> foodMenu = new HashSet<ProductBean>();
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(	name = "restaurant_drinks_menu",
				catalog = "isa_database",
				joinColumns = 
						@JoinColumn(name = "rest_id", nullable = false, updatable = false)
				,
				inverseJoinColumns = 
						@JoinColumn(name = "drink_id", nullable = false, updatable = false)
				
			)
	private Set<ProductBean> drinksMenu = new HashSet<ProductBean>();
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable( name = "restaurant_serves_cuisines",
				catalog = "isa_database",
				joinColumns = 
					@JoinColumn(name = "rest_id", nullable=false,updatable=false),
				inverseJoinColumns = 
					@JoinColumn(name = "type_id", nullable=false,updatable=false)
	)
	private Set<RestaurantFoodTypeBean> foodTypes = new HashSet<RestaurantFoodTypeBean>();
	
	public Set<ReviewBean> getReviews() {
		return reviews;
	}

	public void setReviews(Set<ReviewBean> reviews) {
		this.reviews = reviews;
	}

	public Set<RestaurantFoodTypeBean> getFoodTypes() {
		return foodTypes;
	}

	public void setFoodTypes(Set<RestaurantFoodTypeBean> foodTypes) {
		this.foodTypes = foodTypes;
	}
	
	public Set<ProductBean> getFoodMenu(){
		return this.foodMenu;
	}
	
	public void setFoodMenu(Set<ProductBean> foodMenu){
		this.foodMenu = foodMenu;
	}
	
	public Set<ProductBean> getDrinksMenu(){
		return this.drinksMenu;
	}
	
	public void setDrinksMenu(Set<ProductBean> drinksMenu){
		this.drinksMenu = drinksMenu;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(!(obj instanceof RestaurantBean)){
			return false;
		}
		
		RestaurantBean r = (RestaurantBean)obj;
		if(r.getId() == this.id)
			return true;
		else
			return false;
	};
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public int hashCode() {
		final int prime = 17;
		int result = 1;
		
		result = (int) (1*prime+(prime*this.getId()));
		
		return result;
	};
	
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
	
	@Override
	public String toString(){
		return "This is restaurant ["+this.getId()+"]\n"
				+ "Whos name is ["+this.getName()+"]\n"
						+ "We serve ["+this.getDrinksMenu()+"]\n"
								+ "And ["+this.getFoodMenu()+"]";
	}
}
