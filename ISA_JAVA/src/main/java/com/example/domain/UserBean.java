package com.example.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="User")
public class UserBean implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private String firstName;
	
	@Column(nullable = false)
	private String lastName;
	
	@Column(nullable = false)
	private String username;
	
	@Column(nullable = true)
	private String password;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = true)
	private String profilePicture;
	
	@Column(nullable = true)
	private String auth_code;
	
	@Column(name="requested_deliverer",nullable = true)
	private Integer requested_deliverer;
	
	//@OneToMany(fetch = FetchType.LAZY, mappedBy = "friendsOfUser")
	//private Set<UserBean> usersFriends = new HashSet<UserBean>();
	
	//@ManyToOne(optional = false)
	//private RestaurantBean friendsOfUser;

//	public Set<UserBean> getUsersFriends() {
//		return usersFriends;
//	}
//
//	public void setUsersFriends(Set<UserBean> usersFriends) {
//		this.usersFriends = usersFriends;
//	}
//
//	public RestaurantBean getFriendsOfUser() {
//		return friendsOfUser;
//	}
//
//	public void setFriendsOfUser(RestaurantBean friendsOfUser) {
//		this.friendsOfUser = friendsOfUser;
//	}

	public Integer getRequested_deliverer() {
		return requested_deliverer;
	}

	public void setRequested_deliverer(Integer requested_deliverer) {
		this.requested_deliverer = requested_deliverer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}
	
	public UserBean(){
		
	}
	
	public String getAuth_code() {
		return auth_code;
	}

	public void setAuth_code(String auth_code) {
		this.auth_code = auth_code;
	}

	public UserBean(long id){
		super();
		this.id = id;
	}
	
	public UserBean(String username){
		super();
		this.username = username;
	}
}
