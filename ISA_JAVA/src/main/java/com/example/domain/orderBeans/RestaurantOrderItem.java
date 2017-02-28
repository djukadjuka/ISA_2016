package com.example.domain.orderBeans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.domain.deliveryBeans.DeliveryOrderBean;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Restaurant_order_item")
public class RestaurantOrderItem {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="item_name",nullable = false)
	private String item_name;
	
	@Column(name="item_type",nullable = false)
	private String item_type;
	
	@Column(name="item_price",nullable = false)
	private Float item_price;
	
	@Column(name="order_item_status", nullable = true)
	private String order_item_status;
	
	

		//kojoj porudzbini ovo pripada
		@JsonIgnore
		@ManyToOne
		@JoinColumn(name = "belongs_to_order", nullable = false)
		private RestaurantOrderBean belongs_to_order;

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

		public String getItem_type() {
			return item_type;
		}

		public void setItem_type(String item_type) {
			this.item_type = item_type;
		}

		public Float getItem_price() {
			return item_price;
		}

		public void setItem_price(Float item_price) {
			this.item_price = item_price;
		}

		public RestaurantOrderBean getBelongs_to_order() {
			return belongs_to_order;
		}

		public void setBelongs_to_order(RestaurantOrderBean belongs_to_order) {
			this.belongs_to_order = belongs_to_order;
		}

		public String getOrder_item_status() {
			return order_item_status;
		}

		public void setOrder_item_status(String order_item_status) {
			this.order_item_status = order_item_status;
		}
		

}
