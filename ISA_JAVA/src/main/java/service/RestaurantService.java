package service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import controller.Constants;
import model.RestaurantBean;

@Service
public class RestaurantService {
	
	private HashMap<Integer,RestaurantBean> restaurants = new HashMap<Integer,RestaurantBean>();
	
	public RestaurantService(){
		for(int i=0;	i<20;	i++){
			RestaurantBean r = new RestaurantBean();
			r.setId(i);
			r.setName("Restaurant"+i);
			
			if(i < 5){
				r.setType(Constants.RESTAURANT_TYPE_CHINESE);				
			}else if(i < 10){
				r.setType(Constants.RESTAURANT_TYPE_INDIAN);
			}else if(i < 15){
				r.setType(Constants.RESTAURANT_TYPE_ITALIAN);
			}else{
				r.setType(Constants.RESTAURANT_TYPE_JAPANESE);
			}
			
			restaurants.put(r.getId(), r);
		}
	}
	
	public RestaurantBean getRestaurant(int id){
		return this.restaurants.get(id);
	}
	
	public HashMap<Integer,RestaurantBean> getAllRestaurants(){
		return this.restaurants;
	}
	
}
