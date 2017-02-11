package com.example.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "tables")
public class TableBean implements Serializable {

	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "table_id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "max_people", unique = false, nullable = false)
	private Integer maxPeople;

	@Column(name = "segment", unique = false, nullable = false)
	private String segment;
	
	@Column(name = "smoking", unique = false, nullable = false)
	private String smoking;
	
	@Column(name = "status_table", unique = false, nullable = false)
	private String statusTable;
	

	@Column(name = "image",  nullable = true)
	private String image;
	
	
	
	public TableBean() {
		super();
		this.statusTable = "free";
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	


	public String getSegment() {
		return segment;
	}

	public void setSegment(String segment) {
		this.segment = segment;
	}

	
	public Integer getMaxPeople() {
		return maxPeople;
	}

	public void setMaxPeople(Integer maxPeople) {
		this.maxPeople = maxPeople;
	}

	public String getSmoking() {
		return smoking;
	}

	public void setSmoking(String smoking) {
		this.smoking = smoking;
	}


	public String getStatusTable() {
		return statusTable;
	}

	public void setStatusTable(String statusTable) {
		this.statusTable = statusTable;
	}
	
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	

}
	
	
	

