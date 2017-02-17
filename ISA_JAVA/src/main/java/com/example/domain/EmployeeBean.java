package com.example.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	@Access(AccessType.FIELD)
	private long id;
	
	@OneToOne(cascade = CascadeType.ALL) @MapsId
	@PrimaryKeyJoinColumn
	private UserBean user;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private EmployeeEnum role;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	
	@Column(nullable = true)
	private Float shoeSize;
	
	@Column(nullable = true)
	private Float suitSize;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(	name = "registering_restaurants",
				catalog = "isa_database",
				joinColumns = 
						@JoinColumn(name = "manager_id", nullable = false, updatable = false)
				,
				inverseJoinColumns = 
						@JoinColumn(name = "rest_id", nullable = false, updatable = false)
				
			)
	private Set<RestaurantRegistry> has_registered;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(	name = "manages_restaurants",
				catalog = "isa_database",
				joinColumns = 
						@JoinColumn(name = "manager_id", nullable = false, updatable = false)
				,
				inverseJoinColumns = 
						@JoinColumn(name = "rest_id", nullable = false, updatable = false)
				
			)
	private Set<RestaurantBean> manages;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UserBean getUser() {
		return user;
	}

	public void setUser(UserBean user) {
		this.user = user;
	}

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

	public Float getShoeSize() {
		return shoeSize;
	}

	public void setShoeSize(Float shoeSize) {
		this.shoeSize = shoeSize;
	}

	public Float getSuitSize() {
		return suitSize;
	}

	public void setSuitSize(Float suitSize) {
		this.suitSize = suitSize;
	}

	public Set<RestaurantRegistry> getHas_registered() {
		return has_registered;
	}

	public void setHas_registered(Set<RestaurantRegistry> has_registered) {
		this.has_registered = has_registered;
	}

	public Set<RestaurantBean> getManages() {
		return manages;
	}

	public void setManages(Set<RestaurantBean> manages) {
		this.manages = manages;
	}
	
	
}
