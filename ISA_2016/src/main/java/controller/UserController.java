package controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.UserBean;
import service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService us;
	
	@RequestMapping("/get_all")
	public HashMap<String,UserBean> getAll(){
		return us.getAllUsers();
	}
	
	@RequestMapping("{id}")
	public UserBean getPerson(@PathVariable("id") String id){
		return us.getUser(id);
	}
	
}
