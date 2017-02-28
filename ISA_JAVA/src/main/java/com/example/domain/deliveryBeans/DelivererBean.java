package com.example.domain.deliveryBeans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.example.domain.UserBean;

@Entity
@Table(name="deliverer")
public class DelivererBean {
	
	@Id
	private long id;
	
	@Column(name = "first_login", nullable = true)
	private Long first_login;
	
	@OneToOne(cascade = CascadeType.ALL) @MapsId
	@PrimaryKeyJoinColumn
	private UserBean user;

	@Column(name = "request_status",nullable = true)
	private String request_status;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRequest_status() {
		return request_status;
	}

	public void setRequest_status(String request_status) {
		this.request_status = request_status;
	}

	public UserBean getUser() {
		return user;
	}

	public Long getFirst_login() {
		return first_login;
	}

	public void setFirst_login(Long first_login) {
		this.first_login = first_login;
	}

	public void setUser(UserBean user) {
		this.user = user;
	}
	
	
	
}
