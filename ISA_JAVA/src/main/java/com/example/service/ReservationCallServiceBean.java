package com.example.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.ReservationCallBean;
import com.example.repository.ReservationCallRepository;
import com.example.repository.ReservationRepository;

@Service
public class ReservationCallServiceBean implements ReservationCallService{
	
	@Autowired
	private ReservationCallRepository repository;
	@Autowired
	private ReservationRepository reservationRepository;

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

	@Override
	public void delete(Long reservation_id, Long reservation_call_id) {
		// TODO Auto-generated method stub
		repository.delete(reservation_call_id);
		reservationRepository.delete(reservation_id);
	}

	@Override
	public ReservationCallBean findOne(Long reservationCall) {
		// TODO Auto-generated method stub
		return repository.findOne(reservationCall);
	}

	@Override
	public ReservationCallBean findByKeygenAndId(Long keygen) {
		// TODO Auto-generated method stub
		return repository.findByKeygenAndId(keygen);
	}
}
