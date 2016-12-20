package controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.RestaurantBean;
import service.RestaurantService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

	@Autowired
	private RestaurantService rs;
	
	@RequestMapping("/getAll")
	public HashMap<Integer,RestaurantBean> getAll(){
		return rs.getAllRestaurants();
	}
	
	@RequestMapping("{id}")
	public RestaurantBean getRestaurant(@PathVariable("id") int id){
		return rs.getRestaurant(id);
	}
	
}
