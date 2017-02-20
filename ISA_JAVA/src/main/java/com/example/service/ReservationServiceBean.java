package com.example.service;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.ReservationBean;
import com.example.repository.ReservationRepository;

@Service
public class ReservationServiceBean implements ReservationService{
	
	@Autowired
	private ReservationRepository repository;

	@Override
	public Collection<ReservationBean> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public ReservationBean findOne(Long id) {
		// TODO Auto-generated method stub
		return repository.findOne(id);
	}

	@Override
	public ReservationBean create(ReservationBean reservation) {
		// TODO Auto-generated method stub
		if(reservation.getId() != null){
			return null;
		}
		return repository.save(reservation);
	}

	@Override
	public ReservationBean update(ReservationBean reservation) {
		// TODO Auto-generated method stub
		ReservationBean f = repository.findOne(reservation.getId());
		if(f == null)
			return null;
		return repository.save(reservation);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		repository.delete(id);
	}

	@Override
	public Collection<ReservationBean> findReservationsMatch(Long startDate, Long endDate, Long table_id) {
		// TODO Auto-generated method stub
		return repository.findReservationsMatch(startDate, endDate, table_id);
	}

	@Override
	public Collection<ReservationBean> updateReservation(Long reservation_id, Long startDate, Long endDate,
			Long table_id) {
		// TODO Auto-generated method stub
		return repository.updateReservation(reservation_id, startDate, endDate, table_id);
	}

	@Override
	public Collection<ReservationBean> findReservationsByTableId(Long table_id) {
		// TODO Auto-generated method stub
		return repository.findReservationsByTableId(table_id);
	}

}
