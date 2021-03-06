package com.example.domain;

import java.util.Date;
import java.util.HashSet;
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
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.example.domain.deliveryBeans.DeliveryOrderBean;
import com.example.domain.ratings.ReviewBean;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
	private String role;
	
	@Column(nullable = true)
	private Long dateOfBirth;
	
	@Column(nullable = true)
	private Float shoeSize;
	
	@Column(nullable = true)
	private Float suitSize;
	
	@OneToMany(mappedBy = "for_employee", cascade = CascadeType.ALL)
	private Set<ReviewBean> reviews = new HashSet<>();
	
	@OneToMany(mappedBy = "registering_by", cascade = CascadeType.ALL)
	private Set<RestaurantRegistry> registry = new HashSet<>();
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(	name = "manages_restaurants",
				catalog = "isa_database",
				joinColumns = 
						@JoinColumn(name = "manager_id", nullable = false, updatable = false)
				,
				inverseJoinColumns = 
						@JoinColumn(name = "rest_id", nullable = false, updatable = false)
				
			)
	private Set<RestaurantBean> manages = new HashSet<RestaurantBean>();

	@ManyToOne
	@JoinColumn(name = "works_in_restaurant", nullable = true)
	private RestaurantBean restaurant;
	
	@OneToMany(mappedBy = "for_employee", cascade = CascadeType.ALL)
	private Set<EmployeeScheduleBean> shcedule = new HashSet<>();
	
	@OneToMany(mappedBy = "made_by", cascade = CascadeType.ALL)
	private Set<DeliveryOrderBean> orders;
	
	@JsonIgnore
	public Set<DeliveryOrderBean> getOrders() {
		return orders;
	}

	public void setOrders(Set<DeliveryOrderBean> orders) {
		this.orders = orders;
	}

	@JsonIgnore
	public Set<EmployeeScheduleBean> getShcedule() {
		return shcedule;
	}

	public void setShcedule(Set<EmployeeScheduleBean> shcedule) {
		this.shcedule = shcedule;
	}

	@JsonIgnore
	public RestaurantBean getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(RestaurantBean restaurant) {
		this.restaurant = restaurant;
	}

	public Set<ReviewBean> getReviews() {
		return reviews;
	}

	public void setReviews(Set<ReviewBean> reviews) {
		this.reviews = reviews;
	}

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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}


	public Long getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Long dateOfBirth) {
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

	public Set<RestaurantRegistry> getRegistry() {
		return registry;
	}

	public void setRegistry(Set<RestaurantRegistry> registry) {
		this.registry = registry;
	}

	public Set<RestaurantBean> getManages() {
		return manages;
	}

	public void setManages(Set<RestaurantBean> manages) {
		this.manages = manages;
	}

	public EmployeeBean(long id, UserBean user, String role, Long dateOfBirth, Float shoeSize, Float suitSize,
			Set<RestaurantRegistry> has_registered, Set<RestaurantBean> manages) {
		super();
		this.id = id;
		this.user = user;
		this.role = role;
		this.dateOfBirth = dateOfBirth;
		this.shoeSize = shoeSize;
		this.suitSize = suitSize;
		this.manages = manages;
	}
	
	public EmployeeBean(){};
	
	
}
