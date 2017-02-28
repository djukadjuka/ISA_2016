package com.example.domain.orderBeans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.domain.deliveryBeans.DeliveryOrderItem;

@Entity
@Table(name="Restaurant_order")
public class RestaurantOrderBean {

	@Id
	@GeneratedValue
	private Long id;
	
	
	@Column(name="made_by",nullable=true)
	private Long waiter_id;
	
	@Column(name="table_id",nullable=true)
	private Long table_id;
	
	@Column(name="price",nullable=true)
	private Float price;
	
	@Column(name="order_status", nullable = true)
	private String order_status;
	
	@Column(name="waiter_status", nullable = true)
	private String waiter_status;
	
	@Column(name="cook_i_status", nullable = true)
	private String cook_i_status;
	
	@Column(name="cook_c_status", nullable = true)
	private String cook_c_status;
	
	
	
	@OneToMany(mappedBy = "belongs_to_order", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private Set<RestaurantOrderItem> contains_items = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getWaiter_id() {
		return waiter_id;
	}

	public void setWaiter_id(Long waiter_id) {
		this.waiter_id = waiter_id;
	}

	public Long getTable_id() {
		return table_id;
	}

	public void setTable_id(Long table_id) {
		this.table_id = table_id;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getOrder_status() {
		return order_status;
	}

	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}

	public String getWaiter_status() {
		return waiter_status;
	}

	public void setWaiter_status(String waiter_status) {
		this.waiter_status = waiter_status;
	}

	public String getCook_i_status() {
		return cook_i_status;
	}

	public void setCook_i_status(String cook_i_status) {
		this.cook_i_status = cook_i_status;
	}

	public String getCook_c_status() {
		return cook_c_status;
	}

	public void setCook_c_status(String cook_c_status) {
		this.cook_c_status = cook_c_status;
	}

	public Set<RestaurantOrderItem> getContains_items() {
		return contains_items;
	}

	public void setContains_items(Set<RestaurantOrderItem> contains_items) {
		this.contains_items = contains_items;
	}
	
	
	
	
	
}
