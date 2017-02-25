package com.example.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.ProductBean;
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
	public Collection<ReservationCallBean> findByRecipient(Long recipient) {
		// TODO Auto-generated method stub
		return repository.findByRecipient(recipient);
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

	@Override
	public int updateStatus(String status, Long call_id) {
		// TODO Auto-generated method stub
		return repository.updateStatus(status, call_id);
	}
	
	@Override
	public int updateFoodAndDrink(Long id, ProductBean food, ProductBean drink, int makeOrderFast) {
		// TODO Auto-generated method stub
		return repository.updateFoodAndDrink(id, food, drink, makeOrderFast);
	}

	@Override
	public int cancelFoodAndDrink(Long id) {
		// TODO Auto-generated method stub
		return repository.cancelFoodAndDrink(id);
	}
}
