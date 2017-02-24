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

	enum DEL_STATUS{
		PENDING,
		ACCEPTED
	}
	
	@Id
	private long id;
	
	@OneToOne(cascade = CascadeType.ALL) @MapsId
	@PrimaryKeyJoinColumn
	private UserBean user;

	@Column(name = "request_status",nullable = true)
	@Enumerated(EnumType.STRING)
	private DEL_STATUS request_status;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public DEL_STATUS getRequest_status() {
		return request_status;
	}

	public void setRequest_status(DEL_STATUS request_status) {
		this.request_status = request_status;
	}

	public UserBean getUser() {
		return user;
	}

	public void setUser(UserBean user) {
		this.user = user;
	}
	
	
	
}
