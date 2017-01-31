package com.example.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Employee")
public class EmployeeBean{

	@Id
	private long id;
	
	@OneToOne(cascade = CascadeType.ALL) @MapsId
	@PrimaryKeyJoinColumn
	private UserBean user;
	
	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private EmployeeEnum role;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	
	@Column(nullable = false)
	private float shoeSize;
	
	@Column(nullable = false)
	private float suitSize;

	public EmployeeEnum getRole() {
		return role;
	}

	public void setRole(EmployeeEnum role) {
		this.role = role;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public float getShoeSize() {
		return shoeSize;
	}

	public void setShoeSize(float shoeSize) {
		this.shoeSize = shoeSize;
	}

	public float getSuitSize() {
		return suitSize;
	}

	public void setSuitSize(float suitSize) {
		this.suitSize = suitSize;
	}
	
	
}
