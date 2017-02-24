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
	
	//food
	 
	 @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
		@JoinTable(	name = "Reservations_and_calls",
					catalog = "isa_database",
					joinColumns = 
							@JoinColumn(name = "call_id", nullable = false, updatable = false)
					,
					inverseJoinColumns = 
							@JoinColumn(name = "res_id", nullable = false, updatable = false)
					
				)
	 private Set<ReservationBean> reservations = new HashSet<ReservationBean>();
	 
	 public ReservationCallBean() {
	    }

	    public ReservationCallBean(ReservationStatus status, UserBean originator, UserBean recipient, ReservationBean rb) {
	    	super();
	        this.status = status;
	        this.originator = originator;
	        this.recipient = recipient;
	        this.reservations.add(rb);
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

	public Set<ReservationBean> getReservations() {
		return reservations;
	}

	public void setReservations(Set<ReservationBean> reservations) {
		this.reservations = reservations;
	}
	 
	 
}
