package com.example.domain.deliveryBeans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.domain.EmployeeBean;
import com.example.domain.RestaurantBean;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="delivery_order")	//morao ovako jer ima vec rec order ....
public class DeliveryOrderBean {

	@Id
	@GeneratedValue
	private Long id;
	
	//odakle traje
	@Column(name="date_from", nullable = false)
	private Long date_from;
	
	//dokle traje
	@Column(name="date_to",nullable=true)
	private Long date_to;
	
	//ko je napravio ponudu
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "made_by", nullable = false)
	private EmployeeBean made_by;
	
	@OneToMany(mappedBy = "belongs_to_order", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private Set<DeliveryOrderItem> contains_items = new HashSet<>();

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	private RestaurantBean for_restaurant;
	
	//ciju ponudu je prihvatio
	@OneToOne(cascade = CascadeType.ALL)
	private DelivererBean accepted;
	
	@Column(name="price_accepted",nullable = true)
	private Long price_accepted;
	
	public Set<DeliveryOrderItem> getContains_items() {
		return contains_items;
	}
	
	public void setContains_items(Set<DeliveryOrderItem> contains_items) {
		this.contains_items = contains_items;
	}

	public RestaurantBean getFor_restaurant() {
		return for_restaurant;
	}

	public void setFor_restaurant(RestaurantBean for_restaurant) {
		this.for_restaurant = for_restaurant;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDate_from() {
		return date_from;
	}

	public void setDate_from(Long date_from) {
		this.date_from = date_from;
	}

	public Long getDate_to() {
		return date_to;
	}

	public void setDate_to(Long date_to) {
		this.date_to = date_to;
	}

	public EmployeeBean getMade_by() {
		return made_by;
	}

	public void setMade_by(EmployeeBean made_by) {
		this.made_by = made_by;
	}

	public DelivererBean getAccepted() {
		return accepted;
	}

	public void setAccepted(DelivererBean accepted) {
		this.accepted = accepted;
	}

	public Long getPrice_accepted() {
		return price_accepted;
	}

	public void setPrice_accepted(Long price_accepted) {
		this.price_accepted = price_accepted;
	}
	 
}
