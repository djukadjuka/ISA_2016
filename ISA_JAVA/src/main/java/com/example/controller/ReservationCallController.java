package com.example.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.FriendshipBean;
import com.example.domain.ProductBean;
import com.example.domain.ReservationCallBean;
import com.example.domain.ReservationCallBean.ReservationStatus;
import com.example.service.FriendshipServiceBean;
import com.example.service.ProductServiceBean;
import com.example.service.ReservationCallServiceBean;
import com.example.service.UserServiceBean;

@RestController
public class ReservationCallController {
	
	@Autowired
	public ReservationCallServiceBean reservationCallService = new ReservationCallServiceBean();
	
	@Autowired
	public UserServiceBean userService = new UserServiceBean();
	
	@Autowired
	public ProductServiceBean productService = new ProductServiceBean();
	
	@Autowired
	public FriendshipServiceBean friendshipService = new FriendshipServiceBean();
	
	@Autowired
	private final JavaMailSender mailSender = new JavaMailSenderImpl();
	
	synchronized
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value="/getReservationsForOriginator/{id}",
			method = RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ArrayList<ReservationCallBean> getReservationsForOriginator(@PathVariable("id") Long id)
	{
		Date date = new Date();
		Long time = date.getTime() + 1800000; //30 min pre pocetka rez moze da otkaze
		
		Collection<ReservationCallBean> calls = reservationCallService.findByOriginatorOriginator(id);
		ArrayList<ReservationCallBean> retVal = new ArrayList<>();
		
		Iterator<ReservationCallBean> iterator = calls.iterator();
		
		while(iterator.hasNext())
		{
			ReservationCallBean rcb = iterator.next();
			if(time < rcb.getReservation().getStartDate())
				retVal.add(rcb);
		}
		
		return retVal;
	}
	
	synchronized
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value="/getReservationsForRecipient/{id}",
			method = RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ArrayList<ReservationCallBean> getReservationsForRecipient(@PathVariable("id") Long id)
	{
		Date date = new Date();
		Long time = date.getTime() + 1800000; //30 min pre pocetka rez moze da otkaze jelo
		
		Collection<ReservationCallBean> calls = reservationCallService.findByRecipient(id);
		ArrayList<ReservationCallBean> retVal = new ArrayList<>();
		
		Iterator<ReservationCallBean> iterator = calls.iterator();
		
		while(iterator.hasNext())
		{
			ReservationCallBean rcb = iterator.next();
			if(time < rcb.getReservation().getStartDate())
				retVal.add(rcb);
		}
		
		return retVal;
	}
	
	synchronized
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value="/reservationInvite",
			method = RequestMethod.POST,
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Boolean> reservationInvite(@RequestBody ReservationCallBean rcb)
	{	
		ReservationCallBean call = new ReservationCallBean(ReservationStatus.PENDING, rcb.getOriginator(), rcb.getRecipient(), rcb.getReservation());
		ReservationCallBean callExist = reservationCallService.findByRecipientAndReservation(rcb.getRecipient().getId(), rcb.getReservation().getId());
		
		Boolean bb = new Boolean(false);
		
		if(callExist != null)
			return new ResponseEntity<Boolean> (bb, HttpStatus.NOT_FOUND);
		
		Random r = new Random();
		Long keygen = r.nextLong();
		
		try {
			SimpleMailMessage email = new SimpleMailMessage();
			email.setFrom("SoulFoodApp");
			email.setTo("secimasubre@gmail.com");
			email.setSubject("Restaurant invitation from " + rcb.getOriginator().getFirstName() + " " + rcb.getOriginator().getLastName());
			email.setText("Hello, you have been invited to a restaurant reservation. Follow the link to answer http://localhost:4200/invite/" + keygen);

			mailSender.send(email);
		} catch (Exception ex) {
			System.out.println("Email nije poslat.");
		}
		
			call.setKeygen(keygen);
			reservationCallService.create(call);
			
			Boolean b = new Boolean(true);
		
		return new ResponseEntity<Boolean> (b, HttpStatus.OK);
	}
	
	synchronized
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value="/inviteData/{keygen}",
			method = RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<ReservationCallBean> inviteData(@PathVariable("keygen") Long keygen)
	{
		ReservationCallBean rcb = reservationCallService.findByKeygenAndId(keygen);
		
		if(rcb != null)
			return new ResponseEntity<ReservationCallBean> (rcb, HttpStatus.OK);
		else
			return new ResponseEntity<ReservationCallBean> (rcb, HttpStatus.NOT_FOUND);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value = "/cancelReservation/{res_id}/{res_call_id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public boolean cancelReservation(@PathVariable("res_id") Long res_id, @PathVariable("res_call_id") Long res_call_id){
		
		reservationCallService.delete(res_id, res_call_id);
		
		return true;
	}
	
	synchronized
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value = "/declineInvite",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE
			)
	@ResponseBody
	public boolean declineInvite(@RequestBody Long call_id){
		
		reservationCallService.updateStatus("DECLINED",call_id);
		return true;
	}
	
	synchronized
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value = "/acceptInvite",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE
			)
	@ResponseBody
	public boolean acceptInvite(@RequestBody Long call_id){
		
		reservationCallService.updateStatus("ACCEPTED",call_id);
		return true;
	}
	
	synchronized
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value = "/updateFoodAndDrink",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE
			)
	@ResponseBody
	public boolean updateFoodAndDrink(@RequestBody FoodAndDrinkWrapper foodAndDrink){
		
		System.out.println("FOOOOOOOOOD" + foodAndDrink.getFood());
//		ProductBean food = null;
//		ProductBean drink = null;
//		
//		if(!foodAndDrink.getFood().equals(null))
//			food = productService.findOne(foodAndDrink.getFood());
//		
//		if(!foodAndDrink.getDrink().equals(null))
//			drink = productService.findOne(foodAndDrink.getDrink());
		
		int value = 0;
		
		if(foodAndDrink.isMakeOrderReady())
			value = 1;
		
		reservationCallService.updateFoodAndDrink(foodAndDrink.getReservation_call_id(), foodAndDrink.getFood(), foodAndDrink.getDrink(), value);
		
		return true;
	}
	
	synchronized
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value = "/cancelFoodAndDrink",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE
			)
	@ResponseBody
	public boolean cancelFoodAndDrink(@RequestBody Long id){
		
		reservationCallService.cancelFoodAndDrink(id);
		
		return true;
	}
	 
	// **************************************** HISTORY AND RATINGS ***********************************************
	
	synchronized
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value = "/rateRestaurant",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE
			)
	@ResponseBody
	public boolean rateRestaurant(@RequestBody HashMap<String,String> rates){
		
		Long rest_rate = Long.decode(rates.get("restaurant_rate"));
		Long call_id = Long.decode(rates.get("call_id"));
		Long waiter_rate = Long.decode(rates.get("waiter_rate"));
		Long food_rate = Long.decode(rates.get("food_rate"));
		
		reservationCallService.updateRate(call_id, rest_rate, waiter_rate, food_rate);
		
		return true;
	}
	
	synchronized
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value="/getRestaurantVisitHistory/{id}",
			method = RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<ArrayList<ReservationCallBean>> getRestaurantVisitHistory(@PathVariable("id") Long recipient_id)
	{
		Date d = new Date();
		Long time = d.getTime();
		
		Collection<ReservationCallBean> calls = reservationCallService.findByRecipient(recipient_id);
		ArrayList<ReservationCallBean> retVal = new ArrayList<>();
		
		Iterator<ReservationCallBean> iterator = calls.iterator();
		
		while(iterator.hasNext())
		{
			ReservationCallBean rcb = iterator.next();
			if(time > rcb.getReservation().getEndDate())
				retVal.add(rcb);
		}
		
		return new ResponseEntity<ArrayList<ReservationCallBean>> (retVal, HttpStatus.OK);
	}
	
	//***************** Restaurant rates
	
	synchronized
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value="/getRestaurantAverageRateAll/{restaurant_id}",
			method = RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public float getRestaurantAverageRateAll(@PathVariable("restaurant_id") Long restaurant_id)
	{
		Date d = new Date();
		Long time = d.getTime();
		
		Collection<ReservationCallBean> calls = reservationCallService.findByStatusAccepted();
		
		float numberOfVotes = 0;
		float sumOfVotes = 0;
		
		Iterator<ReservationCallBean> iterator = calls.iterator();
		
		while(iterator.hasNext())
		{
			//Ako je rezervacija prosla, ako je to restoran koji trazimo i ako ima postavljenu ocenu - sracunaj je
			ReservationCallBean rcb = iterator.next();
			if(time > rcb.getReservation().getEndDate() && 
					rcb.getReservation().getTable_id().getRestaurant_zone_id().getRestaurant().getId() == restaurant_id &&
					rcb.getRestaurant_rate() != 0)
			{
				sumOfVotes += rcb.getRestaurant_rate();
				numberOfVotes += 1;
			}
		}
		
		if(numberOfVotes != 0)
			return sumOfVotes / numberOfVotes;
		else
			return 0;
	}
	
	synchronized
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value="/getRestaurantAverageRateFriends/{restaurant_id}/{user_id}",
			method = RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public float getRestaurantAverageRateFriends(@PathVariable("restaurant_id") Long restaurant_id, @PathVariable("user_id") Long user_id)
	{
		Date d = new Date();
		Long time = d.getTime();
		
		Collection<ReservationCallBean> calls = reservationCallService.findByStatusAccepted();
		Collection<FriendshipBean> friendships = friendshipService.findByRecipient_idOrOriginator_idAndStatusAccepted(user_id);
		
		float numberOfVotes = 0;
		float sumOfVotes = 0;
		
		Iterator<ReservationCallBean> iterator = calls.iterator();
		Iterator<FriendshipBean> iteratorFriends = friendships.iterator();
		
		while(iteratorFriends.hasNext())
		{
			FriendshipBean fsb = iteratorFriends.next();
			iterator = calls.iterator();
			while(iterator.hasNext())
			{
				//Ako je rezervacija prosla, ako je to restoran koji trazimo, ako ga je ocenio prijatelj i ako ima postavljenu ocenu - sracunaj je
				ReservationCallBean rcb = iterator.next();
				if(time > rcb.getReservation().getEndDate() && 
						rcb.getReservation().getTable_id().getRestaurant_zone_id().getRestaurant().getId() == restaurant_id &&
						rcb.getRestaurant_rate() != 0 &&
						rcb.getRecipient().getId() == fsb.getId())
				{
					sumOfVotes += rcb.getRestaurant_rate();
					numberOfVotes += 1;
				}
			}
		}
		
		if(numberOfVotes != 0)
			return sumOfVotes / numberOfVotes;
		else
			return 0;
	}
	
	synchronized
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value="/getRestaurantAverageRateMe/{restaurant_id}/{user_id}",
			method = RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public float getRestaurantAverageRateMe(@PathVariable("restaurant_id") Long restaurant_id, @PathVariable("user_id") Long user_id)
	{
		Date d = new Date();
		Long time = d.getTime();
		
		Collection<ReservationCallBean> calls = reservationCallService.findByStatusAccepted();
		
		float numberOfVotes = 0;
		float sumOfVotes = 0;
		
		Iterator<ReservationCallBean> iterator = calls.iterator();
		
		while(iterator.hasNext())
		{
			//Ako je rezervacija prosla, ako je to restoran koji trazimo, ako sam ja ocenio i ako ima postavljenu ocenu - sracunaj je
			ReservationCallBean rcb = iterator.next();
			if(time > rcb.getReservation().getEndDate() && 
					rcb.getReservation().getTable_id().getRestaurant_zone_id().getRestaurant().getId() == restaurant_id &&
					rcb.getRestaurant_rate() != 0 &&
					rcb.getRecipient().getId() == user_id)
			{
				sumOfVotes += rcb.getRestaurant_rate();
				numberOfVotes += 1;
			}
		}
		
		if(numberOfVotes != 0)
			return sumOfVotes / numberOfVotes;
		else
			return 0;
	}
	
	//********** Food rates
	
	synchronized
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value="/getFoodAverageRateAll/{restaurant_id}",
			method = RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public float getFoodAverageRateAll(@PathVariable("restaurant_id") Long restaurant_id)
	{
		Date d = new Date();
		Long time = d.getTime();
		
		Collection<ReservationCallBean> calls = reservationCallService.findByStatusAccepted();
		
		float numberOfVotes = 0;
		float sumOfVotes = 0;
		
		Iterator<ReservationCallBean> iterator = calls.iterator();
		
		while(iterator.hasNext())
		{
			//Ako je rezervacija prosla, ako je to restoran koji trazimo i ako ima postavljenu ocenu - sracunaj je
			ReservationCallBean rcb = iterator.next();
			if(time > rcb.getReservation().getEndDate() && 
					rcb.getReservation().getTable_id().getRestaurant_zone_id().getRestaurant().getId() == restaurant_id &&
					rcb.getFood_rate() != 0)
			{
				sumOfVotes += rcb.getFood_rate();
				numberOfVotes += 1;
			}
		}
		
		if(numberOfVotes != 0)
			return sumOfVotes / numberOfVotes;
		else
			return 0;
	}
	
	synchronized
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value="/getFoodAverageRateFriends/{restaurant_id}/{user_id}",
			method = RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public float getFoodAverageRateFriends(@PathVariable("restaurant_id") Long restaurant_id, @PathVariable("user_id") Long user_id)
	{
		Date d = new Date();
		Long time = d.getTime();
		
		Collection<ReservationCallBean> calls = reservationCallService.findByStatusAccepted();
		Collection<FriendshipBean> friendships = friendshipService.findByRecipient_idOrOriginator_idAndStatusAccepted(user_id);
		
		float numberOfVotes = 0;
		float sumOfVotes = 0;
		
		Iterator<ReservationCallBean> iterator = calls.iterator();
		Iterator<FriendshipBean> iteratorFriends = friendships.iterator();
		
		while(iteratorFriends.hasNext())
		{
			FriendshipBean fsb = iteratorFriends.next();
			iterator = calls.iterator();
			while(iterator.hasNext())
			{
				//Ako je rezervacija prosla, ako je to restoran koji trazimo, ako ga je ocenio prijatelj i ako ima postavljenu ocenu - sracunaj je
				ReservationCallBean rcb = iterator.next();
				if(time > rcb.getReservation().getEndDate() && 
						rcb.getReservation().getTable_id().getRestaurant_zone_id().getRestaurant().getId() == restaurant_id &&
						rcb.getFood_rate() != 0 &&
						rcb.getRecipient().getId() == fsb.getId())
				{
					sumOfVotes += rcb.getFood_rate();
					numberOfVotes += 1;
				}
			}
		}
		
		if(numberOfVotes != 0)
			return sumOfVotes / numberOfVotes;
		else
			return 0;
	}
	
	synchronized
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value="/getFoodAverageRateMe/{restaurant_id}/{user_id}",
			method = RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public float getFoodAverageRateMe(@PathVariable("restaurant_id") Long restaurant_id, @PathVariable("user_id") Long user_id)
	{
		Date d = new Date();
		Long time = d.getTime();
		
		Collection<ReservationCallBean> calls = reservationCallService.findByStatusAccepted();
		
		float numberOfVotes = 0;
		float sumOfVotes = 0;
		
		Iterator<ReservationCallBean> iterator = calls.iterator();
		
		while(iterator.hasNext())
		{
			//Ako je rezervacija prosla, ako je to restoran koji trazimo, ako sam ja ocenio i ako ima postavljenu ocenu - sracunaj je
			ReservationCallBean rcb = iterator.next();
			if(time > rcb.getReservation().getEndDate() && 
					rcb.getReservation().getTable_id().getRestaurant_zone_id().getRestaurant().getId() == restaurant_id &&
					rcb.getFood_rate() != 0 &&
					rcb.getRecipient().getId() == user_id)
			{
				sumOfVotes += rcb.getFood_rate();
				numberOfVotes += 1;
			}
		}
		
		if(numberOfVotes != 0)
			return sumOfVotes / numberOfVotes;
		else
			return 0;
	}
	
	
	//********** Waiter rates
	
	synchronized
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value="/getWaiterAverageRateAll/{restaurant_id}",
			method = RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public float getWaiterAverageRateAll(@PathVariable("restaurant_id") Long restaurant_id)
	{
		Date d = new Date();
		Long time = d.getTime();
		
		Collection<ReservationCallBean> calls = reservationCallService.findByStatusAccepted();
		
		float numberOfVotes = 0;
		float sumOfVotes = 0;
		
		Iterator<ReservationCallBean> iterator = calls.iterator();
		
		while(iterator.hasNext())
		{
			//Ako je rezervacija prosla, ako je to restoran koji trazimo i ako ima postavljenu ocenu - sracunaj je
			ReservationCallBean rcb = iterator.next();
			if(time > rcb.getReservation().getEndDate() && 
					rcb.getReservation().getTable_id().getRestaurant_zone_id().getRestaurant().getId() == restaurant_id &&
					rcb.getWaiter_rate() != 0)
			{
				sumOfVotes += rcb.getWaiter_rate();
				numberOfVotes += 1;
			}
		}
		
		if(numberOfVotes != 0)
			return sumOfVotes / numberOfVotes;
		else
			return 0;
	}
	
	synchronized
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value="/getWaiterAverageRateFriends/{restaurant_id}/{user_id}",
			method = RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public float getWaiterAverageRateFriends(@PathVariable("restaurant_id") Long restaurant_id, @PathVariable("user_id") Long user_id)
	{
		Date d = new Date();
		Long time = d.getTime();
		
		Collection<ReservationCallBean> calls = reservationCallService.findByStatusAccepted();
		Collection<FriendshipBean> friendships = friendshipService.findByRecipient_idOrOriginator_idAndStatusAccepted(user_id);
		
		float numberOfVotes = 0;
		float sumOfVotes = 0;
		
		Iterator<ReservationCallBean> iterator = calls.iterator();
		Iterator<FriendshipBean> iteratorFriends = friendships.iterator();
		
		while(iteratorFriends.hasNext())
		{
			FriendshipBean fsb = iteratorFriends.next();
			iterator = calls.iterator();
			while(iterator.hasNext())
			{
				//Ako je rezervacija prosla, ako je to restoran koji trazimo, ako ga je ocenio prijatelj i ako ima postavljenu ocenu - sracunaj je
				ReservationCallBean rcb = iterator.next();
				if(time > rcb.getReservation().getEndDate() && 
						rcb.getReservation().getTable_id().getRestaurant_zone_id().getRestaurant().getId() == restaurant_id &&
						rcb.getWaiter_rate() != 0 &&
						rcb.getRecipient().getId() == fsb.getId())
				{
					sumOfVotes += rcb.getWaiter_rate();
					numberOfVotes += 1;
				}
			}
		}
		
		if(numberOfVotes != 0)
			return sumOfVotes / numberOfVotes;
		else
			return 0;
	}
	
	synchronized
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(
			value="/getWaiterAverageRateMe/{restaurant_id}/{user_id}",
			method = RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public float getWaiterAverageRateMe(@PathVariable("restaurant_id") Long restaurant_id, @PathVariable("user_id") Long user_id)
	{
		Date d = new Date();
		Long time = d.getTime();
		
		Collection<ReservationCallBean> calls = reservationCallService.findByStatusAccepted();
		
		float numberOfVotes = 0;
		float sumOfVotes = 0;
		
		Iterator<ReservationCallBean> iterator = calls.iterator();
		
		while(iterator.hasNext())
		{
			//Ako je rezervacija prosla, ako je to restoran koji trazimo, ako sam ja ocenio i ako ima postavljenu ocenu - sracunaj je
			ReservationCallBean rcb = iterator.next();
			if(time > rcb.getReservation().getEndDate() && 
					rcb.getReservation().getTable_id().getRestaurant_zone_id().getRestaurant().getId() == restaurant_id &&
					rcb.getWaiter_rate() != 0 &&
					rcb.getRecipient().getId() == user_id)
			{
				sumOfVotes += rcb.getWaiter_rate();
				numberOfVotes += 1;
			}
		}
		
		if(numberOfVotes != 0)
			return sumOfVotes / numberOfVotes;
		else
			return 0;
	}
}

// ********** Wrapper class **********

class FoodAndDrinkWrapper
{
	Long reservation_call_id;
	Long food;
	Long drink;
	boolean makeOrderReady;
	
	public Long getReservation_call_id() {
		return reservation_call_id;
	}
	public void setReservation_call_id(Long reservation_call_id) {
		this.reservation_call_id = reservation_call_id;
	}

	public Long getFood() {
		return food;
	}
	public void setFood(Long food) {
		this.food = food;
	}
	public Long getDrink() {
		return drink;
	}
	public void setDrink(Long drink) {
		this.drink = drink;
	}
	public boolean isMakeOrderReady() {
		return makeOrderReady;
	}
	public void setMakeOrderReady(boolean makeOrderReady) {
		this.makeOrderReady = makeOrderReady;
	}
}

