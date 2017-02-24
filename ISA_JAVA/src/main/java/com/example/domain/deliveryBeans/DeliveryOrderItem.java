package com.example.domain.deliveryBeans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="delivery_order_item")
public class DeliveryOrderItem {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="item_name",nullable = false)
	private String item_name;
	
	@Column(name="item_amount",nullable=false)
	private String item_amount;
	
	//kojoj porudzbini ovo pripada
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "belongs_to_order", nullable = false)
	private DeliveryOrderBean belongs_to_order;
	
	public DeliveryOrderBean getBelongs_to_order() {
		return belongs_to_order;
	}

	public void setBelongs_to_order(DeliveryOrderBean belongs_to_order) {
		this.belongs_to_order = belongs_to_order;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public String getItem_amount() {
		return item_amount;
	}

	public void setItem_amount(String item_amount) {
		this.item_amount = item_amount;
	}
}
