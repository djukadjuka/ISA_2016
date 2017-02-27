package com.example.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Order")
public class OrderBean implements Serializable {

	private static final long serialVersionUID = -8354852449631783895L;

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(nullable = false , name = "price")
	private float price;
	

	@Column(nullable = false , name = "name")
	private String name;
	
	@Column(nullable = false , name = "type")
	private String type;
	
	@Column(nullable = false , name = "from_table")
	private Long table_id;
	
	@Column(nullable = false , name = "served_by")
	private Long waiter_id;
	
	@Column(nullable = false , name = "cook_notification")
	private Long cook_notification;
	
	@Column(nullable = false , name = "waiter_notification")
	private Long waiter_notification;



	public Long getCook_notification() {
		return cook_notification;
	}

	public void setCook_notification(Long cook_notification) {
		this.cook_notification = cook_notification;
	}

	public Long getWaiter_notification() {
		return waiter_notification;
	}

	public void setWaiter_notification(Long waiter_notification) {
		this.waiter_notification = waiter_notification;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
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

	public Long getTable_id() {
		return table_id;
	}

	public void setTable_id(Long table_id) {
		this.table_id = table_id;
	}

	public Long getWaiter_id() {
		return waiter_id;
	}

	public void setWaiter_id(Long waiter_id) {
		this.waiter_id = waiter_id;
	}
	
	
	
	
}
