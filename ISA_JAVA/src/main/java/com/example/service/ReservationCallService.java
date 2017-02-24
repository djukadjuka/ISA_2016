package com.example.service;

import java.util.Collection;

import com.example.domain.ReservationCallBean;

public interface ReservationCallService {
	
	ReservationCallBean create(ReservationCallBean reservationCall);
	
	ReservationCallBean findOne(Long reservationCall);
	
	Collection<ReservationCallBean> findByOriginatorOriginator(Long originator);
	
	void delete(Long reservation_id, Long reservation_call_id);
	
	

}
