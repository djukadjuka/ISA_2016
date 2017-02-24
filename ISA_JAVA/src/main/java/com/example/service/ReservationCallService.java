package com.example.service;

import java.util.Collection;

import com.example.domain.ReservationCallBean;

public interface ReservationCallService {
	
	ReservationCallBean create(ReservationCallBean reservationCall);
	
	Collection<ReservationCallBean> findByOriginatorOriginator(Long originator);

}
