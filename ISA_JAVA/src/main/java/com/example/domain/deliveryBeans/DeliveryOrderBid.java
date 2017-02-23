package com.example.domain.deliveryBeans;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.domain.RestaurantBean;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="delivery_order_bid")
public class DeliveryOrderBid {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="bidding_price")
	private Long bidding_price;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	private DelivererBean made_by_deliverer;
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	private RestaurantBean made_for_restaurant;
	
	@OneToOne(cascade = CascadeType.ALL)
	private DeliveryOrderBean made_for_order;
	
	
	public RestaurantBean getMade_for_restaurant() {
		return made_for_restaurant;
	}

	public void setMade_for_restaurant(RestaurantBean made_for_restaurant) {
		this.made_for_restaurant = made_for_restaurant;
	}

	public DeliveryOrderBean getMade_for_order() {
		return made_for_order;
	}

	public void setMade_for_order(DeliveryOrderBean made_for_order) {
		this.made_for_order = made_for_order;
	}

	public DelivererBean getMade_by_deliverer() {
		return made_by_deliverer;
	}

	public void setMade_by_deliverer(DelivererBean made_by_deliverer) {
		this.made_by_deliverer = made_by_deliverer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBidding_price() {
		return bidding_price;
	}

	public void setBidding_price(Long bidding_price) {
		this.bidding_price = bidding_price;
	}
	
}
