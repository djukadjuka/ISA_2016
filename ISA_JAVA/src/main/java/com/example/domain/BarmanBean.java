package com.example.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "barmans")
public class BarmanBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;

	@Column(name = "email", unique = false, nullable = false)
	private String email;

	@Column(name = "password", unique = false, nullable = false)
	private String password;

	@Column(name = "name", unique = false, nullable = false)
	private String name;

	@Column(name = "surname", unique = false, nullable = false)
	private String surname;

	@Column(name = "birthday", unique = false, nullable = false)
	private String birthday;

	@Column(name = "dress_size", unique = false, nullable = false)
	private String dressSize;

	@Column(name = "shoes_size", unique = false, nullable = false)
	private int shoesSize;

	@Column(name = "stuffType", unique = false, nullable = false)
	private String stuffType;

	@Column(name = "first_login", unique = false, nullable = false)
	private boolean firstLogin;

	public BarmanBean() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getDressSize() {
		return dressSize;
	}

	public void setDressSize(String dressSize) {
		this.dressSize = dressSize;
	}

	public int getShoesSize() {
		return shoesSize;
	}

	public void setShoesSize(int shoesSize) {
		this.shoesSize = shoesSize;
	}

	public String getStuffType() {
		return stuffType;
	}

	public void setStuffType(String stuffType) {
		this.stuffType = stuffType;
	}

	public boolean isFirstLogin() {
		return firstLogin;
	}

	public void setFirstLogin(boolean firstLogin) {
		this.firstLogin = firstLogin;
	}

}

