package controller;

import java.util.ArrayList;

public class Constants {

	public static final String RESTAURANT_TYPE_CHINESE = "Chinese";
	public static final String RESTAURANT_TYPE_ITALIAN = "Italian";
	public static final String RESTAURANT_TYPE_NATIONAL = "National Kitchen";
	public static final String RESTAURANT_TYPE_INDIAN = "Indian";
	public static final String RESTAURANT_TYPE_JAPANESE = "Japanese";
	
	public static final ArrayList<String> getAllRestaurantTypes(){
		ArrayList<String> rts = new ArrayList<String>();
		rts.add(RESTAURANT_TYPE_CHINESE);
		rts.add(RESTAURANT_TYPE_INDIAN);
		rts.add(RESTAURANT_TYPE_ITALIAN);
		rts.add(RESTAURANT_TYPE_JAPANESE);
		rts.add(RESTAURANT_TYPE_NATIONAL);
		return rts;
	}
	
	//public static final String CONSUMABLE_TYPE_BEVERAGE = "Beverage";
	//public static final String CONSUMABLE_TYPE_FOOD		= "Food";
	
	public static float getMean(ArrayList<Integer> array){
		int x = 0;
		for(int i=0;	i<array.size();	i++){
			x+=array.get(i);
		}
		return x/array.size();
	}
}
