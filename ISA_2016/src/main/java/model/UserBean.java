package model;

public class UserBean {
	
	private String email;
	private String username;	//PK
	private String password;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getID(){
		return this.username;
	}
	public void setID(String username){
		this.username = username;
	}
	
}
