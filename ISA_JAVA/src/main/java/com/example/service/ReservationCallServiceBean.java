package com.example.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.ReservationCallBean;
import com.example.repository.ReservationCallRepository;

@Service
public class ReservationCallServiceBean implements ReservationCallService{
	
	@Autowired
	private ReservationCallRepository repository;

	@Override
	public ReservationCallBean create(ReservationCallBean reservationCall) {
		// TODO Auto-generated method stub
		if(reservationCall != null)
			return repository.save(reservationCall);
		else
			return null;
	}

	@Override
	public Collection<ReservationCallBean> findByOriginatorOriginator(Long originator) {
		// TODO Auto-generated method stub
		return repository.findByOriginatorOriginator(originator);
	}
}
