package service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import model.UserBean;

@Service
public class UserService {
	
	private HashMap<String,UserBean> users = new HashMap<String,UserBean>();
	
	public UserService(){
		for(int i=0;	i<10;	i++){
			UserBean u = new UserBean();
			u.setEmail("user_"+i+"@gmail.com");
			u.setPassword("password"+i);
			u.setUsername("user"+i);
			users.put(u.getID(), u);
		}
	}
	
	public UserBean getUser(String username){
		return this.users.get(username);
	}
	
	public HashMap<String,UserBean> getAllUsers(){
		return users;
	}
	
}
