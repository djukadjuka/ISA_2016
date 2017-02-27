package com.example.service;

import java.util.Collection;

import org.springframework.data.repository.query.Param;

import com.example.domain.ProductBean;
import com.example.domain.ReservationCallBean;

public interface ReservationCallService {
	
	ReservationCallBean create(ReservationCallBean reservationCall);
	
	ReservationCallBean findOne(Long reservationCall);
	
	Collection<ReservationCallBean> findByOriginatorOriginator(Long originator);
	
	Collection<ReservationCallBean> findByRecipient(Long recipient);
	
	void delete(Long reservation_id, Long reservation_call_id);
	
	ReservationCallBean findByKeygenAndId(Long keygen);
	
	//**** for invites and emails
	
	ReservationCallBean findByRecipientAndReservation(Long recipient, Long reservation_id);
	
	int updateStatus(String status, Long call_id );
	
	int updateFoodAndDrink(Long id, ProductBean food, ProductBean drink, int makeOrderFast);

	int cancelFoodAndDrink(Long id);
	
	//**** history and rates
	
	int updateRate(Long call_id, Long rest_rate, Long waiter_rate, Long food_rate);
	
	Collection<ReservationCallBean> findByStatusAccepted();

}
