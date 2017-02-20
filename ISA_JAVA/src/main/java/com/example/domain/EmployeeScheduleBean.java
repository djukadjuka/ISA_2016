package com.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="employee_schedule")
public class EmployeeScheduleBean {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="to_date")
	private Long to;
	
	@Column(name="from_date")
	private Long from;
	
	@Column(name="date_date")
	private Long date;
	
	@ManyToOne
	@JoinColumn(name = "for_employee", nullable = true)
	private EmployeeBean for_employee;
	
	public EmployeeBean getFor_employee() {
		return for_employee;
	}

	public void setFor_employee(EmployeeBean for_employee) {
		this.for_employee = for_employee;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTo() {
		return to;
	}

	public void setTo(Long to) {
		this.to = to;
	}

	public Long getFrom() {
		return from;
	}

	public void setFrom(Long from) {
		this.from = from;
	}

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}
	
}
