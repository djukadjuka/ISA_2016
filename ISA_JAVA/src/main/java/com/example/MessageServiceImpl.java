package com.example;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService{

	@Autowired
	private MessageRepo repository;
	
	@Override
	public Collection<SimpleMessage> findAll() {
		return this.repository.findAll();
	}

	@Override
	public SimpleMessage create(SimpleMessage message) {
		return this.repository.save(message);
	}

	@Override
	public SimpleMessage getMessageWithSomeId(Long id) {
		return this.repository.getMessageWithSomeId(id);
	}

}
