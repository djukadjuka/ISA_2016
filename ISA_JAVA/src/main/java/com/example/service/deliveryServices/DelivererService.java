package com.example.service.deliveryServices;

import java.util.Collection;

import com.example.domain.deliveryBeans.DelivererBean;

public interface DelivererService {
	
	DelivererBean findOne(Long id);
	
	Collection<DelivererBean> findAll();
	
	DelivererBean update(DelivererBean deliverer);
	
	DelivererBean create(DelivererBean deliverer);
	
	void delete(Long id);
	
	//custom
	public void deliverer_accepted(Long user_id);
	public void user_wants_to_be_deliverer(Long user_id);
	public void deliverer_changed_password(Long user_id);
}
