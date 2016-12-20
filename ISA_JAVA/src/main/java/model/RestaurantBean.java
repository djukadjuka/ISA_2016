package model;

import java.util.ArrayList;
import java.util.HashMap;

public class RestaurantBean {
	
	private int id;
	private String type;
	private String name;
	
	private HashMap<String,ConsumableBean> foodMenu = new HashMap<String,ConsumableBean>();
	private HashMap<String,ArrayList<Integer>> foodGrades = new HashMap<String,ArrayList<Integer>>(); 
	
	private HashMap<String,ConsumableBean> drinksMenu = new HashMap<String,ConsumableBean>();
	
	private HashMap<String,UserBean> waiters = new HashMap<String,UserBean>();
	private HashMap<String,ArrayList<Integer>> waiterGrades = new HashMap<String,ArrayList<Integer>>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public HashMap<String, ConsumableBean> getFoodMenu() {
		return foodMenu;
	}
	public void setFoodMenu(HashMap<String, ConsumableBean> foodMenu) {
		this.foodMenu = foodMenu;
	}
	public HashMap<String, ArrayList<Integer>> getFoodGrades() {
		return foodGrades;
	}
	public void setFoodGrades(HashMap<String, ArrayList<Integer>> foodGrades) {
		this.foodGrades = foodGrades;
	}
	public HashMap<String, ConsumableBean> getDrinksMenu() {
		return drinksMenu;
	}
	public void setDrinksMenu(HashMap<String, ConsumableBean> drinksMenu) {
		this.drinksMenu = drinksMenu;
	}
	public HashMap<String, UserBean> getWaiters() {
		return waiters;
	}
	public void setWaiters(HashMap<String, UserBean> waiters) {
		this.waiters = waiters;
	}
	public HashMap<String, ArrayList<Integer>> getWaiterGrades() {
		return waiterGrades;
	}
	public void setWaiterGrades(HashMap<String, ArrayList<Integer>> waiterGrades) {
		this.waiterGrades = waiterGrades;
	}
}
