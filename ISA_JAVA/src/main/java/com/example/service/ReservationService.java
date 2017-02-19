package com.example.service;

import java.util.Collection;

import com.example.domain.ReservationBean;

public interface ReservationService {
	
	Collection<ReservationBean> findReservationsByTableId(Long table_id);
	
	Collection<ReservationBean> findReservationsMatch(Long startDate,Long endDate, Long table_id);
	
	Collection<ReservationBean> updateReservation(Long reservation_id, Long startDate, Long endDate, Long table_id);
	
	Collection<ReservationBean> findAll();
	
	ReservationBean findOne(Long id);
	
	ReservationBean create(ReservationBean reservation);
	
	ReservationBean update(ReservationBean reservation);
	
	void delete(Long id);

}
