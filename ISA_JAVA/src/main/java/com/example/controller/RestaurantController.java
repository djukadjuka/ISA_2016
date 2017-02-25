package com.example.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.domain.ProductBean;
import com.example.domain.RestaurantBean;
import com.example.domain.RestaurantFoodTypeBean;
import com.example.service.RestaurantService;
import com.example.service.RestaurantServiceBean;

@RestController
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService = new RestaurantServiceBean();
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value="/basicRestaurantUpdate",
					method=RequestMethod.POST,
					consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<RestaurantBean> basicRestaurantUpdate(@RequestBody RestaurantBean restaurant){
		//must change name,type,food types, foods and drinks
		
		String new_name = restaurant.getName();
		String type = restaurant.getType();
		Long key = restaurant.getId();
		HashSet<RestaurantFoodTypeBean> food_types =  (HashSet<RestaurantFoodTypeBean>) restaurant.getFoodTypes();
		HashSet<ProductBean> foods = (HashSet<ProductBean>) restaurant.getFoodMenu();
		HashSet<ProductBean> drinks = (HashSet<ProductBean>) restaurant.getDrinksMenu();
		
		System.out.println("Name : " + new_name);
		System.out.println("Type : " + type);
		System.out.println("Key : " + key);
		System.out.println("----FOODS---");
		for(ProductBean food : foods){
			System.out.println("\tId : " + food.getId());
			System.out.println("\tName : " + food.getName());
		}
		System.out.println("----DRINKS-----");
		for(ProductBean food : drinks){
			System.out.println("\tId : " + food.getId());
			System.out.println("\tName : " + food.getName());
		}
		System.out.println("----FOOD_TYPES-----");
		for(RestaurantFoodTypeBean food_type: food_types){
			System.out.println("\tId : " + food_type.getId());
			System.out.println("\tName : " + food_type.getName());
		}
		
		this.restaurantService.updateRestaurantName_FIX(new_name, key);
		this.restaurantService.updateRestaurantType_FIX(type, key);
		this.restaurantService.update_restaurant_coords(restaurant.getLat(), restaurant.getLng(),key);
		
		RestaurantBean existing_restaurant = this.restaurantService.findOne(key);
		Set<RestaurantFoodTypeBean> e_food_types =  existing_restaurant.getFoodTypes();
		Set<ProductBean> e_foods = existing_restaurant.getFoodMenu();
		Set<ProductBean> e_drinks = existing_restaurant.getDrinksMenu();
		
		//modif food types
		for(RestaurantFoodTypeBean f : e_food_types){
			if(!food_types.contains(f)){
				this.restaurantService.delete_food_type(key, f.getId());
			}
		}
		for(RestaurantFoodTypeBean f : food_types){
			if(!e_food_types.contains(f)){
				this.restaurantService.insert_new_food_type(key, f.getId());
			}
		}
		
		//modif foods
		for(ProductBean f : e_foods){
			if(!foods.contains(f)){
				this.restaurantService.delete_food_item(key, f.getId());
			}
		}
		for(ProductBean f : foods){
			if(!e_foods.contains(f)){
				this.restaurantService.inset_food_item(key, f.getId());
			}
		}
		
		//modif drinks
		for(ProductBean f : e_drinks){
			if(!drinks.contains(f)){
				this.restaurantService.delete_drink_item(key, f.getId());
			}
		}
		for(ProductBean f : drinks){
			if(!e_drinks.contains(f)){
				this.restaurantService.inset_drink_item(key, f.getId());
			}
		}
		
		RestaurantBean modif = this.restaurantService.findOne(key);
		
		return new ResponseEntity<RestaurantBean>(modif,HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value="/createRestaurant",method=RequestMethod.PUT,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RestaurantBean> createRestaurant(@RequestBody RestaurantBean restaurant){
		
		RestaurantBean r = restaurantService.create(restaurant);
		
		return new ResponseEntity<RestaurantBean>(r,HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value = "/getRestaurant/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<RestaurantBean> getRestaurant(@PathVariable("id") Long id){
		RestaurantBean restaurant = restaurantService.findOne(id);
		if(restaurant == null){
			return new ResponseEntity<RestaurantBean>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<RestaurantBean>(restaurant,HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value = "/filterRestaurants",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Collection<RestaurantBean>> getRestaurant(@RequestBody HashMap<String, String> filter){
		
		Collection<RestaurantBean> restaurants = null;
		
		String name = filter.get("restaurantName").trim();
		String type = filter.get("restaurantType");
		
		if(!name.equals("") && !type.equals(""))
			restaurants = restaurantService.filterRestaurantsByNameAndType(name, type);
		else if(!name.equals(""))
			restaurants = restaurantService.filterRestaurantsByName(name);
		else if(!type.equals(""))
			restaurants = restaurantService.filterRestaurantsByType(type);
		
		if(restaurants == null){
			return new ResponseEntity<Collection<RestaurantBean>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Collection<RestaurantBean>>(restaurants,HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value="/getAllRestaurants",
			method = RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<ArrayList<RestaurantBean>> getAllRestaurants(){
		ArrayList<RestaurantBean> allRestaurants = (ArrayList<RestaurantBean>) restaurantService.findAll();
		
		return new ResponseEntity<ArrayList<RestaurantBean>>(allRestaurants,HttpStatus.OK);
	}
	
	private long restId;
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value="/prepForUpload/{id}",method=RequestMethod.GET)
	public ResponseEntity<String> prepForUpload(@PathVariable("id") Long id){
		restId = id;
		return new ResponseEntity<String>("Sent",HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value = "/uploadImage",
			headers=("content-type=multipart/*"),
			method = RequestMethod.POST
			)
	@ResponseBody
	public ResponseEntity<TESTFILEINFO> uploadImage(@RequestParam("file") MultipartFile image) throws IOException{
		
		String dir = System.getProperty("user.dir");
		
		Path pth = Paths.get(dir,"../","ISA_ANGULAR/src/assets/pictures/restaurant_pictures");
		System.out.println(pth);
		
		TESTFILEINFO fileInfo = new TESTFILEINFO();
		HttpHeaders headers = new HttpHeaders();
		
		if (!image.isEmpty()) {
			try {
				String filename = image.getOriginalFilename();
				
				File destination = new File(pth +  File.separator + restId + ".jpg");
				image.transferTo(destination);
				
				fileInfo.setFileName(destination.getPath());
				fileInfo.setFileSize(image.getSize());
				
				headers.add("File Uploaded Successfully - ", filename);
				
				return new ResponseEntity<TESTFILEINFO>(fileInfo, headers, HttpStatus.OK);
			}catch (Exception e){    
				return new ResponseEntity<TESTFILEINFO>(HttpStatus.BAD_REQUEST);
			}
		}else{
			return new ResponseEntity<TESTFILEINFO>(HttpStatus.BAD_REQUEST);
		}
	}
	
}

class TESTFILEINFO {

	private String fileName;
	private long fileSize;
	
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public long getFileSize() {
		return fileSize;
	}
	
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
}