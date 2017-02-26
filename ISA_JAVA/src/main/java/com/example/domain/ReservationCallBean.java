package com.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Reservation_call")
public class ReservationCallBean {
	
	public enum ReservationStatus {
        PENDING,
        ACCEPTED,
        DECLINED
    }

	@Id
	@GeneratedValue
	private long id;
	
	@Enumerated(EnumType.STRING)
	private ReservationStatus status;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "originator_id", nullable = false)
    private UserBean originator;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "recipient_id", nullable = false)
    private UserBean recipient;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reservation", nullable = false)
    private ReservationBean reservation;
	
	@Column(nullable = true)
	private Long keygen;
	
	@Column(nullable = true)
	private int makeOrderFast;
	
	//food and drinks
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "food", nullable = true)
    private ProductBean food;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "drink", nullable = true)
    private ProductBean drink;
	
	//ratings
	@Column(nullable = true)
	private int restaurant_rate;
	
	@Column(nullable = true)
	private int waiter_rate;
	
	@Column(nullable = true)
	private int food_rate;
	 
	 public ReservationCallBean() {
	    }

	    public ReservationCallBean(ReservationStatus status, UserBean originator, UserBean recipient, ReservationBean rb) {
	    	super();
	        this.status = status;
	        this.originator = originator;
	        this.recipient = recipient;
	        this.reservation = rb;
	    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ReservationStatus getStatus() {
		return status;
	}

	public void setStatus(ReservationStatus status) {
		this.status = status;
	}

	public UserBean getOriginator() {
		return originator;
	}

	public void setOriginator(UserBean originator) {
		this.originator = originator;
	}

	public UserBean getRecipient() {
		return recipient;
	}

	public void setRecipient(UserBean recipient) {
		this.recipient = recipient;
	}

	public ReservationBean getReservation() {
		return reservation;
	}

	public void setReservation(ReservationBean reservation) {
		this.reservation = reservation;
	}

	public void setKeygen(Long keygen) {
		this.keygen = keygen;
	}

	public int getMakeOrderFast() {
		return makeOrderFast;
	}

	public void setMakeOrderFast(int makeOrderFast) {
		this.makeOrderFast = makeOrderFast;
	}

	public ProductBean getFood() {
		return food;
	}

	public void setFood(ProductBean food) {
		this.food = food;
	}

	public int getRestaurant_rate() {
		return restaurant_rate;
	}

	public void setRestaurant_rate(int restaurant_rate) {
		this.restaurant_rate = restaurant_rate;
	}

	public int getWaiter_rate() {
		return waiter_rate;
	}

	public void setWaiter_rate(int waiter_rate) {
		this.waiter_rate = waiter_rate;
	}

	public int getFood_rate() {
		return food_rate;
	}

	public void setFood_rate(int food_rate) {
		this.food_rate = food_rate;
	}

	public ProductBean getDrink() {
		return drink;
	}

	public void setDrink(ProductBean drink) {
		this.drink = drink;
	}
}
