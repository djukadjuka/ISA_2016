package com.example.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="restaurant_food_type")
public class RestaurantFoodTypeBean {

	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false)
	private String name;
	
	@ManyToMany(mappedBy = "restaurantFoodTypes")
	private Set<RestaurantBean> restaurantsOfThisType = new HashSet<RestaurantBean>();

	@Override
	public int hashCode(){
		int prime = 13;
		int result = 1;
		result = (int) (result*prime+(prime*this.getId()));
		return result;
	}
	
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof RestaurantFoodTypeBean))
			return false;
		
		RestaurantFoodTypeBean type = (RestaurantFoodTypeBean) obj;
		if(type.getId() == this.getId())
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

	public Set<RestaurantBean> getRestaurantsOfThisType() {
		return restaurantsOfThisType;
	}

	public void setRestaurantsOfThisType(Set<RestaurantBean> restaurantsOfThisType) {
		this.restaurantsOfThisType = restaurantsOfThisType;
	}
	
}
