package com.example.service.deliveryServices;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.deliveryBeans.DelivererBean;
import com.example.repository.delivery.DelivererRepository;

@Service
public class DelivererServiceBean implements DelivererService{

	@Autowired
	private DelivererRepository repository;
	
	@Override
	public DelivererBean findOne(Long id) {
		return this.repository.findOne(id);
	}

	@Override
	public Collection<DelivererBean> findAll() {
		return this.repository.findAll();
	}

	@Override
	public DelivererBean update(DelivererBean deliverer) {
		return this.repository.save(deliverer);
	}

	@Override
	public DelivererBean create(DelivererBean deliverer) {
		return this.repository.save(deliverer);
	}

	@Override
	public void delete(Long id) {
		this.repository.delete(id);
	}

	@Override
	public void deliverer_accepted(Long user_id) {
		this.repository.deliverer_accepted(user_id);
	}

	@Override
	public void user_wants_to_be_deliverer(Long user_id) {
		this.repository.user_wants_to_be_deliverer(user_id);
	}

	@Override
	public void deliverer_changed_password(Long user_id) {
		this.repository.deliverer_changed_password(user_id);
	}

}
