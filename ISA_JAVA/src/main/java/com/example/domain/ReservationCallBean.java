package com.example.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	
	//food
	 
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
}
