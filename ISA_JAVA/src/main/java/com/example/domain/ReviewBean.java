package com.example.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ReviewBean {

	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false)
	private String comment;
	
	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private RatingEnum rating;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dateOfReview;
	
	@ManyToOne(optional = false)
	private RestaurantBean restaurant;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public RatingEnum getRating() {
		return rating;
	}

	public void setRating(RatingEnum rating) {
		this.rating = rating;
	}

	public Date getDateOfReview() {
		return dateOfReview;
	}

	public void setDateOfReview(Date dateOfReview) {
		this.dateOfReview = dateOfReview;
	}

	public RestaurantBean getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(RestaurantBean restaurant) {
		this.restaurant = restaurant;
	}
	
}
