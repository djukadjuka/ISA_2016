package com.example.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

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

import com.example.domain.RestaurantBean;
import com.example.service.RestaurantService;
import com.example.service.RestaurantServiceBean;

@RestController
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService = new RestaurantServiceBean();
	
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
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value = "/updateRestaurant",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	@ResponseBody
	public ResponseEntity<RestaurantBean> updateRestaurant(@RequestBody RestaurantBean restaurant){
		
		RestaurantBean r = restaurantService.findOne((long) restaurant.getId());
		
		if(r == null){
			return new ResponseEntity<RestaurantBean>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		r.setName(restaurant.getName());
		r.setType(restaurant.getType());
		r.setDrinksMenu(restaurant.getDrinksMenu());
		r.setFoodMenu(restaurant.getFoodMenu());
		r.setFoodTypes(restaurant.getFoodTypes());
		restaurantService.update(r);
		
		return new ResponseEntity<RestaurantBean>(r,HttpStatus.OK);
		
	}
	
	private long restId;
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value="/prepForUpload/{id}",method=RequestMethod.GET)
	public ResponseEntity<String> prepForUpload(@PathVariable("id") Long id){
		System.out.println(id);
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
		
		System.out.println(System.getProperty("user.dir"));
		
		TESTFILEINFO fileInfo = new TESTFILEINFO();
		HttpHeaders headers = new HttpHeaders();
		
		if (!image.isEmpty()) {
			try {
				String filename = image.getOriginalFilename();
				
				File destination = new File("C:\\Users\\Djuka\\Desktop\\FOLDER_STRUCTURE"+  File.separator + restId + ".jpg");
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