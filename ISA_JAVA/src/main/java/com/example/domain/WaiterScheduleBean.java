package com.example.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "waiterssSchedule")
public class WaiterScheduleBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "waiter_sch_id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "waitersDate", unique = false, nullable = true)
	private String waitersDate;

	@Column(name = "startTime", unique = false, nullable = true)
	private String startTime;

	@Column(name = "stopTime", unique = false, nullable = true)
	private String stopTime;

	public WaiterScheduleBean() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getWaitersDate() {
		return waitersDate;
	}

	public void setWaitersDate(String waitersDate) {
		this.waitersDate = waitersDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getStopTime() {
		return stopTime;
	}

	public void setStopTime(String stopTime) {
		this.stopTime = stopTime;
	}

}
