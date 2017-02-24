package com.example.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Reservation")
public class ReservationBean {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false, name = "start_date")
	private Long startDate;
	
	@Column(nullable = false, name = "end_date")
	private Long endDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "table_id", nullable = false)
    private TableBean table_id;
    
    @ManyToMany(mappedBy = "reservations")
	private Set<ReservationCallBean> reservationCalls = new HashSet<ReservationCallBean>();

    public ReservationBean() {
    }

    public ReservationBean(TableBean t) {
        this.table_id = t;
    }
    
    public Long getId() {
        return id;
    }

	public Long getStartDate() {
		return startDate;
	}

	public void setStartDate(Long startDate) {
		this.startDate = startDate;
	}

	public Long getEndDate() {
		return endDate;
	}

	public void setEndDate(Long endDate) {
		this.endDate = endDate;
	}

	public TableBean getTable_id() {
		return table_id;
	}

	public void setTable_id(TableBean table_id) {
		this.table_id = table_id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}

