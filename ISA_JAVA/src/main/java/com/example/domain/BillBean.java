package com.example.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bills")
public class BillBean implements Serializable {

	private static final long serialVersionUID = -8354852449631783895L;

	@Id
	@GeneratedValue
	@Column(name = "bill_id", unique = true, nullable = false)
	private Integer id;

	public BillBean() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}

